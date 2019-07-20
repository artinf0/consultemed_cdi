<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.acme.business.*"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="br.com.acme.model.*" %>
<%@page import="br.com.acme.dao.*" %>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:admin_tamplate title="Dashboard">
	
	
	<jsp:attribute name="content">
	
		<section class="content">
		
		
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/dashboard">Home</a></li>
		    <li class="breadcrumb-item active" aria-current="page">Médicos</li>
		  </ol>
		</nav>
		 
		<div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Novo Agendamento</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            
            <form role="form" method="POST" action="${pageContext.request.contextPath}/admin/agendamentos?action=list">
              <div class="box-body">
              
              	<div class="form-group col-md-12">
					<input type="hidden" name="id" value="${agendamento.id}" readonly="readonly" required="required" class="form-control" id="id" placeholder="Digite o nome">
				</div>
              
                <div class="form-group">
                  <label for="medico">CRM Médico</label>
                  <input type="text" name="crm" class="form-control" value="${agendamento.medico.crm}" id="medico" placeholder="Informe o Médico">
                </div>

                  <div class="form-group">
                      <label for="paciente">CPF Paciente</label>
                      <input type="text" name="cpf" class="form-control" value="${agendamento.paciente.cpf}" id="paciente" placeholder="Informe o Paciente">
                  </div>
                
                <div class="form-group">
                  <label for="dataAgendamento">Data Agendamento</label>
                  <input type="date" name="dataAgendamento" class="form-control" value="${agendamento.dataAgendamento}" id="dataAgendamento" placeholder="Informe a Data">
                </div>
                                             
              </div>

              <div class="box-footer">
					<c:if test="${agendamento.id != null}">
						<button type="submit" class="btn btn-warning">Editar</button>
	            	</c:if>
					
					<c:if test="${agendamento.id == null}">
						<button name="enviar" type="submit" class="btn btn-primary">Salvar</button>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<a href="${pageContext.request.contextPath}/admin/agendamentos?action=list" class="btn btn-sm btn-default">Agendamentos</a>&nbsp;&nbsp;&nbsp;&nbsp;
              </div>
            </form>
      	</div>
			
	 </section>
  
	</jsp:attribute>
</mt:admin_tamplate>
