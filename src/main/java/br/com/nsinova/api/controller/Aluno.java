/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.nsinova.api.controller;

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
@Path("aluno")
public class Aluno {

    @POST
    @Path("manter")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response manter(br.com.nsinova.teste.modelo.Aluno aluno) throws Exception {
        br.com.nsinova.teste.conexao.Conexao conexao = null;
        try {
            conexao = new br.com.nsinova.teste.conexao.Conexao();

            br.com.nsinova.teste.negocio.Pessoa pessoaNegocio = new br.com.nsinova.teste.negocio.Pessoa(conexao.getConnection());
            br.com.nsinova.teste.negocio.Aluno alunoNegocio = new br.com.nsinova.teste.negocio.Aluno(conexao.getConnection());

            conexao.autoCommit();
            if (pessoaNegocio.manter(aluno, "Aluno") <= 0 || alunoNegocio.manter(aluno) <= 0) {
                throw new Exception("Nenhuma linha alterada");
            }
            conexao.commit();

            return Response.ok().build();
        } catch (Exception ex) {
            if (conexao != null && !conexao.isClosed()) {
                conexao.rollback();
            }
            throw new Exception("Erro ao manter aluno", ex);
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }

    @POST
    @Path("alterar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(br.com.nsinova.teste.modelo.Aluno aluno) throws Exception {
        br.com.nsinova.teste.conexao.Conexao conexao = null;
        try {
            conexao = new br.com.nsinova.teste.conexao.Conexao();

            br.com.nsinova.teste.negocio.Pessoa pessoaNegocio = new br.com.nsinova.teste.negocio.Pessoa(conexao.getConnection());
            br.com.nsinova.teste.negocio.Aluno alunoNegocio = new br.com.nsinova.teste.negocio.Aluno(conexao.getConnection());

            conexao.autoCommit();
            if (pessoaNegocio.alterar(aluno) <= 0 || alunoNegocio.alterar(aluno) <= 0) {
                throw new Exception("Nenhuma linha alterada");
            }
            conexao.commit();

            return Response.ok().build();
        } catch (Exception ex) {
            if (conexao != null && !conexao.isClosed()) {
                conexao.rollback();
            }
            throw new Exception("Erro ao alterar aluno", ex);
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }
    
    @POST
    @Path("excluir")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(br.com.nsinova.teste.modelo.Aluno aluno) throws Exception {
        br.com.nsinova.teste.conexao.Conexao conexao = null;
        try {
            conexao = new br.com.nsinova.teste.conexao.Conexao();

            br.com.nsinova.teste.negocio.Pessoa pessoaNegocio = new br.com.nsinova.teste.negocio.Pessoa(conexao.getConnection());
            
            conexao.autoCommit();
            if (pessoaNegocio.excluir(aluno) <= 0) {
                throw new Exception("Nenhuma linha alterada");
            }
            conexao.commit();

            return Response.ok().build();
        } catch (Exception ex) {
            if (conexao != null && !conexao.isClosed()) {
                conexao.rollback();
            }
            throw new Exception("Erro ao excluir aluno", ex);
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }
}
