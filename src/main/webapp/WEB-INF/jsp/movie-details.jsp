<%@include file="includes/header.jsp" %>

<div class="main" style="margin: 10px auto;padding:0 5px">

    <div style="padding: 20px;" >
        <table>
            <tr>
                <td>
                    <img style="width: 300px;border-radius: 20px;" src="${movie.poster_path}" class="center-block img-rounded "/>
                </td>
                <td>
                    <div style="margin: 20px;padding: 30px">
                        <h1 class="movie-header label label-primary">${movie.title}</h1>
                        <h3><b>Overview : </b>${movie.overview}</h3>
                        <h3><b>Release Date : </b>${movie.release_date}</h3>
                        <h3><b>Vote Average : </b>${movie.vote_average}</h3>

                        <c:if test="${is_fav eq false}" >
                            <form method="post" action="">
                                <button type="submit" class="btn btn-info" >Add To Favourites </button>
                            </form>  

                        </c:if>

                        <c:if test="${is_fav eq true}" >

                            <form:form method="post" action="${movie_id}/delete" >
                                <button class="btn btn-danger" type="submit" >Remove from Favourites </button>
                            </form:form>
                        </c:if>


                    </div>
                </td>
            </tr>

        </table>
    </div>

    <div class="panel panel-danger" style="margin-top: 20px" >
        <div class="panel-heading text-center" style="font-size: 22px;font-weight: bold" >
            Trailers
        </div>
        <div class="panel-body" >
            <div class="col-lg-4">
                <ul class="playlist" >
                    <% int i = 1;%>
                    <c:forEach items="${trailers}" var="trailer" >
                        <li class="btn" value="${trailer}">
                            <span class="glyphicon glyphicon-play" ></span>   Trailer <%= i++%>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-lg-8" >
                <iframe id="frame"  src="${trailers[0]}" frameborder="2" allowfullscreen></iframe>
            </div>

        </div>
    </div>




</div>

<script>
    $(document).ready(function () {

    });

</script>

<%@include file="includes/footer.jsp" %>