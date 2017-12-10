<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='Wereldwijnen' />
<body>
	<v:menu />
	<c:if test="${not empty land}">
		<h2>Soorten uit ${land.naam}</h2>
		<ul>
			<c:forEach items="${land.soorten}" var="landSoort">
				<li ${landSoort.naam}><c:url value='/index.htm' var='indexURL'>
						<c:param name='landId' value='${land.id}' />
						<c:param name='soortId' value='${landSoort.id}' />
					</c:url> <a href='${indexURL}'> ${landSoort.naam} </a></li>
			</c:forEach>
		</ul>
	</c:if>
	<label><span>${fouten.id}</span></label>
	<c:if
		test="${not empty soort and soort.land.id eq land.id and empty fouten}">
		<h2>Wijnen uit ${soort.naam}</h2>
		<ul>
			<c:forEach items="${soort.wijnen}" var="soortWijn">
				<li ${soortWijn.jaar}><c:url value='/wijn/toevoegen.htm'
						var='wijnURL'>
						<c:param name='wijnId' value='${soortWijn.id}' />
					</c:url> <a href='${wijnURL}'> ${soortWijn.jaar} 
					<c:forEach begin="1" end="${soortWijn.beoordeling}">&#9733;</c:forEach></a></li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>
