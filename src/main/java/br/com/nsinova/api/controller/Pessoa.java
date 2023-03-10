/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.api.controller;

import java.sql.Connection;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author miqueias.nadaluti
 */
@Path("pessoa")
public class Pessoa {

    @GET
    @Path("obterLista")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obterLista(@QueryParam("textoPesquisa") String textoPesquisa) throws Exception {
        br.com.nsinova.teste.conexao.Conexao conexao = null;
        try {
            conexao = new br.com.nsinova.teste.conexao.Conexao();

            br.com.nsinova.teste.negocio.Pessoa pessoaNegocio = new br.com.nsinova.teste.negocio.Pessoa(conexao.getConnection());
            List<br.com.nsinova.teste.modelo.Pessoa> pessoas = pessoaNegocio.obterLista(textoPesquisa);

            return Response.ok(pessoas).build();
        } catch (Exception ex) {
            throw new Exception("Erro ao obter lista", ex);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception ex) {
                    throw new Exception("Erro ao obter lista", ex);
                }
            }
            if (conexao.isClosed()) {
                Response.ok("Toma paulowisc").build();
            }
        }
    }

    @GET
    @Path("obterPorCpf")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obterPorCpf(@QueryParam("cpf") String cpf) throws Exception {
        br.com.nsinova.teste.conexao.Conexao conexao = null;
        try {
            conexao = new br.com.nsinova.teste.conexao.Conexao();

            br.com.nsinova.teste.negocio.Pessoa pessoaNegocio = new br.com.nsinova.teste.negocio.Pessoa(conexao.getConnection());
            br.com.nsinova.teste.modelo.Pessoa pessoa = pessoaNegocio.obterPorCpf(cpf);

            return Response.ok(pessoa).build();
        } catch (Exception ex) {
            throw new Exception("Erro ao obter pessoa", ex);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception ex) {
                    throw new Exception("Erro ao obter pessoa", ex);
                }
            }
            if (conexao.isClosed()) {
                Response.ok("Toma paulowisc").build();
            }
        }
    }

    @POST
    @Path("excluir")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(br.com.nsinova.teste.modelo.Pessoa pessoa) throws Exception {
        br.com.nsinova.teste.conexao.Conexao conexao = null;
        try {
            conexao = new br.com.nsinova.teste.conexao.Conexao();

            br.com.nsinova.teste.negocio.Pessoa pessoaNegocio = new br.com.nsinova.teste.negocio.Pessoa(conexao.getConnection());

            conexao.autoCommit();
            if (pessoaNegocio.excluir(pessoa) <= 0) {
                throw new Exception("Nenhuma linha alterada");
            }
            conexao.commit();

            return Response.ok().build();
        } catch (Exception ex) {
            if (conexao != null && !conexao.isClosed()) {
                conexao.rollback();
            }
            throw new Exception("Erro ao excluir pessoa", ex);
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }
}
