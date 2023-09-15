package com.appco.retrofitcomkotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.appco.retrofitcomkotlin.api.EnderecoAPI
import com.appco.retrofitcomkotlin.api.RetrofitHelper
import com.appco.retrofitcomkotlin.databinding.ActivityMainBinding
import com.appco.retrofitcomkotlin.model.Endereco
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            binding.progressBar.visibility = View.GONE

            buttonBuscarCep.setOnClickListener {
                GlobalScope.launch {

                    val cep = binding.textInputCep.text.toString()

                    if(cep.isNotEmpty()) {
                        if (cep.length == 8) {
                            recuperarEndereco(cep)
                        }else {
                            runOnUiThread {
                                binding.textInputCep.error = "Formato de CEP inválido"
                            }
                        }
                    }else {
                        runOnUiThread {
                            binding.textInputCep.error = "Digite o cep"
                        }
                    }
                }
            }
        }
    }

    private suspend fun recuperarEndereco(cep: String) {

        runOnUiThread {
            binding.progressBar.visibility = View.VISIBLE
            binding.buttonBuscarCep.isEnabled = false
        }

        var retorno: Response<Endereco>? = null

        try {
            val enderecoAPI = retrofit.create(EnderecoAPI::class.java)
            retorno = enderecoAPI.recuperarEndereco(cep)
        }catch (e: Exception) {
            runOnUiThread {
                binding.progressBar.visibility = View.GONE
                binding.buttonBuscarCep.isEnabled = true
            }
            e.printStackTrace()
            Log.i("info_endereco", "Erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val endereco = retorno.body()

                val intent = Intent(this, ResultadoBuscaActivity::class.java)
                intent.putExtra("enderecoRecuperado", endereco)
                startActivity(intent)

                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    binding.buttonBuscarCep.isEnabled = true
                    binding.textInputCep.setText("")
                }
            }
        }
    }
}