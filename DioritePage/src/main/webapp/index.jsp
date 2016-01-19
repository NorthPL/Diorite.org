<% final String baseUrl = org.diorite.web.page.server.DioritePageServer.getInstance().getBaseUrl(); %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="<%=baseUrl%>/static/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="<%=baseUrl%>/static/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

        <script>var baseUrl="<%=baseUrl%>";</script>
        <script src="<%=baseUrl%>/static/js/jquery.min.js"></script>
        <script src="<%=baseUrl%>/static/js/materialize.min.js"></script>
        <script src="<%=baseUrl%>/static/js/dioritepage/dioritepage.nocache.js"></script>
    </head>
    <body>
        <nav class="white" role="navigation">
            <div class="nav-wrapper container">
                <a id="logo-container" class="brand-logo"></a>
                <ul id="menu-entries-computer" class="right hide-on-med-and-down">
                    <a id="profile-login-button" class="waves-effect waves-light btn"></a>
                </ul>

                <ul id="nav-mobile" class="side-nav">
                    <!-- TODO -->
                </ul>
                <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
            </div>
        </nav>

        <div id="content"></div>
    </body>
</html>
