<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <jsp:include page="Menu.jsp"/>
            <div class="content">
                <div class="main">
                    <div class="tittle">
                        ${top1.title}
                    </div>
                    <div class="image">
                        <img src="images/${top1.image}"/>
                    </div>
                    <div class="text">
                        ${top1.description}
                    </div>
                    <div class="signature">
                        <p>
                        <div class="icon1"></div>
                        <div class="icon2"></div>
                        By ${top1.author} | ${top1.dateConvert}
                        </p>
                    </div>
                </div>
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
