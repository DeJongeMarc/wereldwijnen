<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='Wereldwijnen' />
<body>
	<v:menu />
	<label><span>${fouten.land}</span></label>
	<c:if test="${not empty land}">
		<h2>Soorten uit ${land.naam}</h2>
		<ul>
			<c:forEach items="${land.soorten}" var="landSoort">
				<li ${landSoort.naam}><c:url
						value='/index.htm' var='indexURL'>
						<c:param name='landId' value='${land.id}' />
						<c:param name='soortId' value='${landSoort.id}' />
					</c:url> <a href='${indexURL}'> ${landSoort.naam} </a></li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>
