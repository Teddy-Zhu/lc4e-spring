<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head><c:import url="/WEB-INF/views/common/title.jsp"></c:import></head>
<body>
<c:import url="/WEB-INF/views/common/header.jsp"></c:import>

<h1>${exception}</h1>
<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>

<script src="/plugins/require.js/2.1.17/require.min.js" data-main="/js/index"></script>
</body>
</html>
