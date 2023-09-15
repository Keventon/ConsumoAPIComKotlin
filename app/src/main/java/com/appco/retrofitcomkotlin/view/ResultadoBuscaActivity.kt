package com.appco.retrofitcomkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appco.retrofitcomkotlin.databinding.ActivityResultadoBuscaBinding
import com.appco.retrofitcomkotlin.model.Endereco

class ResultadoBuscaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBuscaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBuscaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val bundle = intent.extras

            if (bundle != null) {
                val endereco = bundle.getSerializable("enderecoRecuperado") as Endereco

                if (endereco.cep.isNotEmpty()) {
                    textCep.text = "CEP: ${endereco.cep}"
                }else {
                    textCep.text = "CEP: Não encontrado"
                }

                if (endereco.bairro.isNotEmpty()) {
                    textBairro.text = "Bairro: ${endereco.bairro}"
                }else {
                    textBairro.text = "Bairro: Não disponível"
                }

                if (endereco.complemento.isNotEmpty()) {
                    textComplemento.text = "Complemento: ${endereco.complemento}"
                }else {
                    textComplemento.text = "Complemento: Não disponível"
                }

                if (endereco.localidade.isNotEmpty()) {
                    textLocalidade.text = "Localidade: ${endereco.localidade}"
                }else {
                    textLocalidade.text = "Localidade: Não disponível"
                }

                if (endereco.uf.isNotEmpty()) {
                    textUf.text = "UF: ${endereco.uf}"
                }else {
                    textUf.text = "UF  : Não disponível"
                }

                if (endereco.logradouro.isNotEmpty()) {
                    textLogradouro.text = "Logradouro: ${endereco.logradouro}"
                }else {
                    textLogradouro.text = "Logradouro  : Não disponível"
                }

                if (endereco.ddd.isNotEmpty()) {
                    textDdd.text = "DDD: ${endereco.ddd}"
                }else {
                    textDdd.text = "DDD  : Não disponível"
                }

                if (endereco.ibge.isNotEmpty()) {
                    textIbge.text = "IBGE: ${endereco.ibge}"
                }else {
                    textIbge.text = "IBGE  : Não disponível"
                }

                if (endereco.gia.isNotEmpty()) {
                    textGia.text = "GIA: ${endereco.gia}"
                }else {
                    textGia.text = "GIA  : Não disponível"
                }

                if (endereco.siafi.isNotEmpty()) {
                    textSiafi.text = "SIAFI: ${endereco.siafi}"
                }else {
                    textSiafi.text = "SIAFI  : Não disponível"
                }

            }

            buttonRealizarNovaBusca.setOnClickListener {
                finish()
            }
        }
    }
}