<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id='menu'>
	<h1>Wereldwijnen</h1>
	<ul class='lijst-horizontaal'>
		<c:forEach items='${landen}' var='land'>
			<c:url value='/index.htm' var='landUrl'>
				<c:param name='landId' value='${land.id}' />
			</c:url>
			<li><a href='${landUrl}'><img alt='${land.naam}'
					src='images/${land.id}.png'></a></li>
		</c:forEach>
	</ul>
	<c:if test='${not empty winkelmandje}'>
		<a href='<c:url value='/winkelmandje.htm'/>'>
		<img alt='winkelmandje' src='images/mandje.png'></a>
	</c:if>
</nav>
