<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<!doctype html>
<html lang='nl'>
<v:head title="Bevestiging"/>
<body>
<c:choose>
<c:when test="${not empty bevestigdBonId}">
<h1>Je mandje is bevestigd als bestelbon ${bevestigdBonId}</h1>
</c:when>
<c:otherwise>
<h1>Je bestelling is niet bevestigd!</h1>
</c:otherwise>
</c:choose>
<h1></h1>
<a href="<c:url value="/index.htm"/>">Terug naar overzicht</a>
</body>
</html>