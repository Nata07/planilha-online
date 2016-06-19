<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="pt-br">
<head>
    <meta name="viewport" content="width=device-width, maximum-scale=1">

    <title>Página Inicial</title>

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
</head>
<body>
<!-- Barra de navegação -->
<nav class="navbar navbar-default navbar-fixed-top minha-nav">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">
              Toggle navigation
            </span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="#">
                <figure class="animated delay-03s fadeInDown">
                    <img class="logo" src="<c:url value="/img/icon-55px.png"/>" alt="Logo">
                </figure>
            </a>

        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav main-nav">
                <li class="ativo">
                    <a href="#">
                        Home
                    </a>
                </li>
                <li>
                    <a href="#caracteristicas">
                        Caracteristicas
                    </a>
                </li>
                <li>
                    <a href="#">
                        Contato
                    </a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle links-nav" data-toggle="dropdown" role="button"
                       aria-haspopup="true"
                       aria-expanded="false">
                        Entre
                    </a>
                    <ul class="dropdown-menu" id="login-dp">
                        <li>
                            <div class="row">
                                <div class="col-md-12 dropdown-nav">
                                    <span class="animated fadeInDown delay-03s">
                                        Entre
                                    </span>
                                    <form action="<c:url value="/validar"/>" class="form form-nav " method="post"
                                          role="form">
                                        <div class="form-group">
                                            <input type="text"
                                                   class="form-control input-dropdown animated slideInLeft delay-03s"
                                                   name="pessoa.usuario" placeholder="Usuario" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password"
                                                   class="form-control input-dropdown animated slideInRight delay-03s"
                                                   name="pessoa.senha" placeholder="Senha" required>
                                            <div class="help-block text-right">
                                                <a class="animated slideInRight delay-03s" href="<c:url value='/abrirModalSenha'/>">
                                                    Esqueceu a senha?
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit"
                                                    class="btn btn-block btn-fb animated fadeInUp delay-03s transicao-04s">
                                                Entrar
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>


                <li>
                    <a href="" class="nao-clicavel">
                        Ou
                    </a>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle links-nav" data-toggle="dropdown" role="button"
                       aria-haspopup="true"
                       aria-expanded="false">
                        Cadastre-se
                    </a>
                    <ul class="dropdown-menu" id="cadastro-dp">
                        <li class="row">
                            <div class="col-md-12 dropdown-nav">
                                <span class="titulo-form animated fadeInDown delay-03s">
                                    Cadastro
                                </span>
                                <form action="<c:url value='/cadastrar'/>" class="form form-nav" method="post"
                                      role="form">
                                    <div class="form-group">
                                        <input type="text"
                                               class="form-control input-dropdown animated slideInRight delay-03s"
                                               name="pessoa.nome" placeholder="Nome" required>
                                    </div>

                                    <div class="form-group">
                                        <input type="email"
                                               class="form-control input-dropdown animated slideInLeft delay-03s"
                                               name="pessoa.email" placeholder="E-mail" required>
                                    </div>

                                    <div class="form-group">
                                        <input type="text"
                                               class="form-control input-dropdown animated slideInLeft delay-03s"
                                               name="pessoa.usuario" placeholder="Usuario" required>
                                    </div>

                                    <div class="form-group">
                                        <input type="password"
                                               class="form-control input-dropdown animated slideInRight delay-03s"
                                               name="pessoa.senha" placeholder="Senha" required>
                                    </div>

                                    <div class="form-group">
                                        <button type="submit"
                                                class="btn btn-block btn-fb animated fadeInUp delay-03s transicao-04s">
                                            Cadastrar-se!
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>


<!-- Carrossel -----------------------------------
==================================================-->
<div id="carrossel" class="carousel carousel-fade hidden-xs" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carrossel" data-slide-to="0" class="active"></li>
        <li data-target="#carrossel" data-slide-to="1"></li>
        <li data-target="#carrossel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="im1" src="<c:url value="/img/inner-banner-new-2.jpg"/>" alt="...">
        </div>
        <div class="item">
            <img class="img2" src="<c:url value="/img/banner-financas-pessoais.jpg"/>" alt="...">
        </div>
        <div class="item">
            <img class="img3" src="<c:url value="/img/banner-gestao-e-financas.jpg"/>" alt="...">
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carrossel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Anterior</span>
    </a>
    <a class="right carousel-control" href="#carrossel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Próximo</span>
    </a>
</div>

<!-- Conteudo 1 -----------------------------------
==================================================-->
<div class="container marketing" id="caracteristicas">

    <!-- Three columns of text below the carousel -->
    <div class="row conteudo">
        <div class="col-lg-4">
            <img class="img-circle imgtop" src="<c:url value="/img/cartao.jpg"/>" alt="Generic placeholder image" width="140"
                 height="140">
            <h2 class="tituloIcone">Gastos com cartão de crédito </h2>
            <p>Cadastre seus gastos com seu cartão de crédito e veja qual a melhor forma de não ter gastos
                desnecessários.</p>
        </div><!-- /.col-lg-4 -->

        <div class="col-lg-4">
            <img class="img-circle" src="<c:url value="/img/controle.jpg"/>" alt="Generic placeholder image" width="140"
                 height="140">
            <h2 class="tituloIcone">Controle seus gastos</h2>
            <p>Controle seu dinheiro, veja o que acontece em um so lugar. Receba aviso de gasto ou estouro, e nao perca
                mais seu sono.</p>
        </div><!-- /.col-lg-4 -->

        <div class="col-lg-4">
            <img class="img-circle " src="<c:url value="/img/circulo_peq_estat_5.png"/>" alt="Generic placeholder image"
                 width="140" height="140">
            <h2 class="tituloIcone">Planeje seus investimentos </h2>
            <p>Trace suas metas e acompanhe automaticamente quanto gastou ou ainda quanto irá gastar em cada
                categoria.</p>
        </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading" style="margin-top: 50px; font-family: sans-serif;">Cadastros rápidos. </h2>
            <p class="lead">Com o NF Finananças você tem a facilidade nos cadastros de receitas e dispesas. Você poderar
                cadastrar todos seus dados com poucos cliques. </p>
        </div>
        <div class="col-md-5">
            <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto"
                 style="margin-top: 20px;"
                 alt="Generic placeholder" src="<c:url value="/img/cadastro.png"/>">

        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
            <h2 class="featurette-heading" style="margin-top: 50px; font-family: sans-serif;"> Relatórios completos</h2>
            <p class="lead">O NF Financias possiu o relatório completo para você. Tenha todos os meses, um relatorio
                simplificado para controle pessoal.</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
            <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto"
                 alt="Generic placeholder image" src="<c:url value="/img/icone_azul.png"/>" style="margin-top: 20px;">
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading margin-top-10">Segurança </h2>
            <p class="lead">Segurança, uma das principais vantagem do NF Finanças. Tenha certeza que seus dados estao
                seguros usando nossa aplicação.</p>
        </div>
        <div class="col-md-5">
            <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto"
                 alt="Generic placeholder image" src="<c:url value="/img/seguranca.jpg"/>">
        </div>
    </div>

    <hr class="featurette-divider">

    <!-- /END THE FEATURETTES -->

    <section class="main-section contact" id="contact">

        <div class="row">
            <div class="col-lg-6 col-sm-7 wow fadeInLeft">
                <div class="contact-info-box address clearfix">
                    <h3><i class=" icon-map-marker"></i>Address:</h3>
                    <span>308 Negra Arroyo Lane<br>Albuquerque, New Mexico, 87111.</span>
                </div>
                <div class="contact-info-box phone clearfix">
                    <h3><i class="fa-phone"></i>Fone:</h3>
                    <span>0800-08-0800</span>
                </div>
                <div class="contact-info-box email clearfix">
                    <h3><i class="fa-pencil"></i>E-mail:</h3>
                    <span>contato@nafa.com</span>
                </div>
                <div class="contact-info-box hours clearfix">
                    <h3>
                        <i class="fa-clock-o"></i>Horário:
                    </h3>
                    <span>
                        <strong>Segunda - Quinta-Feira:</strong>
                        10h - 18h
                        <br>
                        <strong>Sexta-Feira:</strong>
                        As pessoas ainda trabalham na sexta?
                        <br>
                        <strong>Sábado - Domingo:</strong>
                        Só loucos.
                    </span>
                </div>
                <ul class="social-link">
                    <li class="twitter"><a href="#"><i class="fa-twitter"></i></a></li>
                    <li class="facebook"><a href="#"><i class="fa-facebook"></i></a></li>
                    <li class="github"><a href="#"><i class="fa-github"></i></a></li>
                    <li class="gplus"><a href="#"><i class="fa-google-plus"></i></a></li>
                    <li class="linkedin"><a href="#"><i class="fa-linkedin"></i></a></li>
                </ul>
            </div>
            <div class="col-lg-6 col-sm-5 wow fadeInUp delay-05s">
                <div class="form" name="dados">
                    <input class="input-text" type="text" name="nome" value="O seu nome *"
                           onfocus="if(this.value==this.defaultValue)this.value='';"
                           onblur="if(this.value=='')this.value=this.defaultValue;" required>
                    <input class="input-text" type="email" name="email" value="O seu e-mail *"
                           onfocus="if(this.value==this.defaultValue)this.value='';"
                           onblur="if(this.value=='')this.value=this.defaultValue;" required>
                    <textarea class="input-text text-area" name="textArea" cols="0" rows="0"
                              onfocus="if(this.value==this.defaultValue)this.value='';"
                              onblur="if(this.value=='')this.value=this.defaultValue;">Sua mensagem *</textarea>
                    <input class="input-btn" type="submit" value="Enviar" required>
                </div>
            </div>
        </div>
    </section>
</div>

<footer class="footer">
    <div class="container">
        <div class="footer-logo"><a href="#"><img src="<c:url value="/img/icon-45px.png"/>" alt=""></a></div>
        <span class="glyphicon glyphicon-copyright-mark copyright"> Copyright 2015 | by NAFA System</span>
    </div>
</footer>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>

<script type="text/javascript">
    $(window).load(function () {

        $('.main-nav li a').bind('click', function (event) {
            var $anchor = $(this);

            $('html, body').stop().animate({
                scrollTop: $($anchor.attr('href')).offset().top - 102
            }, 1500, 'easeInOutExpo');

            event.preventDefault();
        });
    })
</script>


</body>
</html>