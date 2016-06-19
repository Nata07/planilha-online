<%--
  Created by IntelliJ IDEA.
  User: Luiz Fachin
  Date: 11/06/2016
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, maximum-scale=1">

    <title>Planilha de Gastos Online</title>

    <link rel="icon" href="<c:url value="/img/icon-32px.png"/>" type="image/png">

    <!--Fontes-->
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,800italic,700italic,600italic,400italic,300italic,800,700,600'
          rel='stylesheet' type='text/css'>

    <!--CSS-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/animate.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/font-awesome.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/carousel.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/simple-sidebar.css"/>">
</head>
<body>

<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <ul class="nav sidebar-nav">
            <li class="sidebar-brand menuLateral">
                <a href="#">
                    <figure>
                        <img src="<c:url value="/img/icon-55px.png"/>" alt="">
                    </figure>
                </a>
            </li>
            <li>
                <a href="<c:url value='/'/>">Página Inicial</a>
            </li>
            <li class="ativo">
                <a href="#">Demonstrativo</a>
            </li>
            <li>
                <a href="<c:url value='/receitas'/>">Cadastrar Receitas</a>
            </li>
            <li>
                <a href="<c:url value='/despesas'/>">Cadastrar Despesas</a>
            </li>
        </ul>
    </nav>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <h1>Demonstrativo</h1>
                    <p>Bem vindo ${nomeUsuario}!</p>
                    <span>Seu saldo atual é R$: (pegar valor do saldo e mostrar vermelho, amarelo ou verde)</span>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->


<!-- jQuery -->
<script src="<c:url value="/js/jquery.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script>
    $(document).ready(function () {
        var trigger = $('.hamburger'),
                overlay = $('.overlay'),
                isClosed = false;

        trigger.click(function () {
            hamburger_cross();
        });

        function hamburger_cross() {

            if (isClosed == true) {
                overlay.hide();
                trigger.removeClass('is-open');
                trigger.addClass('is-closed');
                isClosed = false;
            } else {
                overlay.show();
                trigger.removeClass('is-closed');
                trigger.addClass('is-open');
                isClosed = true;
            }
        }

        $('[data-toggle="offcanvas"]').click(function () {
            $('#wrapper').toggleClass('toggled');
        });
    });
</script>

</body>
</html>