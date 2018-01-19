
<%@include file="includes/header.jsp" %>
    <div class="main" >
        <c:forEach items="${movies}" var="movie" >
        <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 movie-div">
            <span class="vote" >${movie.vote_average}</span>
            <a href="${cat_type}/${movie.id}"> 
                <img style="display: block;margin: 0 auto;width: 80%;height: 88%" src="${movie.poster_path}" /> 
            </a>
            <h3 style="text-align: center;height: 50px;">${movie.title}</h3>
        </div>
    </c:forEach>      
</div>

<%@include file="includes/footer.jsp" %>