<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="t" class="model.DigitalModel" scope="request"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Right</title>
        <link href="css/right.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="right">
            <div class="newst">
                <div class="titleNews">
                    <span>Digital News</span>
                </div>
                <div class="contentNews">
                    ${t.top1Digital.shortDes}
                </div>
            </div>
            <div class="newst">
                <div class="titleNews">
                    <span>Search</span><br>
                </div>
                <form action="SearchControl?index=1" method="post">
                    <input type="text" name="txtSearch" id="txtSearch" value="" size="15" class="searchBox">
                    <input type="submit" name="btnGo" value="Go" id="btnGo" class="searchButton">
                </form>                        
            </div>
            <div class="newst">
                <div class="titleNews">
                    <span>Last Articles</span><br>
                </div>
                <c:forEach items="${t.top5Next}" var="i">
                    <a href="../src/java/controller/SearchControl.java"></a>
                    <div class="lastArticles">
                        <a href="HomePage?id=${i.id}"> ${i.title}</a>
                    </div>
                </c:forEach>
            </div>
        </div>    
    </body>
</html>
