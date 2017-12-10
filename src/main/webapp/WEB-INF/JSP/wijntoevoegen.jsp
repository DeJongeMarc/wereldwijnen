<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib prefix="v" uri="http://vdab.be/tags"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!doctype html>
<html lang='nl'>
<v:head title ="Wijn toevoegen aan mandje"/>
<body>
	<h1>Wijn toevoegen aan mandje</h1>
	<a href="<c:url value="/index.htm"/>">Terug naar overzicht</a>
	<p>${fouten.wijn}</p>
	<c:if test="${not empty wijn}">
	<table>
		<tbody>
			<tr>
				<td>Land</td>
				<td>${wijn.soort.land.naam}</td>
			</tr>
			<tr>
				<td>Soort</td>
				<td>${wijn.soort.naam}</td>
			</tr>
			<tr>
				<td>Jaar</td>
				<td>${wijn.jaar}</td>
			</tr>
			<tr>
				<td>Beoordeling</td>
				<td><c:forEach begin='1' end='${wijn.beoordeling}'>
						<span>&#9733;</span>
					</c:forEach></td>
			</tr>
			<tr>
				<td>Prijs</td>
				<td>${wijn.prijs}</td>
			</tr>
		</tbody>
	</table>
	<form method='post' id='toevoegform'>
		<label>Aantal flessen<span>${fouten.aantal}</span>
			<input type='number' name='aantal' value='${aantal}' min='1' required autofocus></label>
		<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			if (!navigator.cookieEnabled) {
				alert("Dit werkt enkel als cookies aanstaan");
				return false;
			}
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
	</c:if>
</body>
</html>