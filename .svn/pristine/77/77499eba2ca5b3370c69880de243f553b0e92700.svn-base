<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 4. 4.     	KHT            최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
.article-card article {
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  padding: 1.5rem;
}

.article-card:hover article {
  transform: translateY(-5px); /* 살짝 위로 이동 */
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15); /* 그림자 강조 */
  border-color: #0d6efd; /* 테두리 색 파란색으로 */
}
.article-card img {
  filter: brightness(60%);
}
</style>

<!-- 페이지 상단 타이틀 -->
<div class="page-title dark-background" data-aos="fade" 
style="background-image: url('${pageContext.request.contextPath}/resources/BizPage/assets/img/page-title-bg.jpg');">
  <div class="container position-relative">
    <h1>채용</h1>
<!--     <p>Esse dolorum voluptatum ullam est sint nemo et est ipsa porro placeat quibusdam quia assumenda numquam molestias.</p> -->
    <nav class="breadcrumbs">
      <ol>
        <li><a href="${pageContext.request.contextPath }/home">Home</a></li>
        <li class="current">Recruit</li>
      </ol>
    </nav>
  </div>
</div>
<!-- 본문  -->
<div class="page-heading">
	<section class="section">
		<!-- section-title  -->
		<div class="container section-title" data-aos="fade-up">
			<h2 id="boardTitle">공고</h2>
			<p>
				전자산업을 선도하는 대덕우리전자는 <strong>창의성과 문제 해결력을 갖춘 인재</strong>, 
				<strong>협업과 커뮤니케이션에 능한 인재</strong>, 그리고 <strong>끊임없이 성장하는 인재</strong>를 기다리고 있습니다. <br/>
				여러분의 도전이 미래를 바꿉니다.
			</p>
		</div>
		<!-- section-card  -->
		<div class="card" style="border: none;">
<!-- 			<div class="card-header"></div> -->
			<div class="card-body">
				<!-- 연도 반기 선택(hidden) -->
				<div class="d-flex align-items-center gap-2" style="display: none !important;">
					<fmt:setLocale value="ko_KR"/>
					<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy" var="currentYear"/>
					<fmt:formatDate value="<%= new java.util.Date() %>" pattern="MM" var="currentMonth"/>
				    <select id="yearSelect" class="form-select w-auto">
				        <c:forEach var="i" begin="2020" end="${currentYear }">
				            <option value="${i}" ${i == currentYear ? 'selected' : ''}>${i}년</option>
				        </c:forEach>
				    </select>
				    <select id="halfSelect" class="form-select w-auto">
				        <option value="상" ${currentMonth <= 6 ? 'selected' : ''}>상반기</option>
        				<option value="하" ${currentMonth > 6 ? 'selected' : ''}>하반기</option>
				    </select>
				    <button class="btn btn-outline-primary" id="searchButton">조회</button>
				</div>
				
				<!-- *************************************** article ************************************* -->
				<div class="container px-1">
					<div class="row gx-5 gy-5">
						<c:choose>
							<c:when test="${not empty recruitBoardList}">
								<c:forEach var="recruitBoard" items="${recruitBoardList}">
								<!-- 한 줄에 3개 -->
								<div class="col-12 col-sm-6 col-lg-4 d-flex  article-card " 
										data-startdate="<fmt:formatDate value='${recruitBoard.recruitStartdate}' pattern='yyyy-MM-dd' />"
										data-enddate="<fmt:formatDate value='${recruitBoard.recruitEnddate}' pattern='yyyy-MM-dd' />">
									
									<article class="flex-fill d-flex flex-column border rounded shadow-sm h-100 p-3"
									         style="min-height: 300px; max-height: 400px; font-size: 0.85rem;">
									
										<!-- 썸네일 -->
										<c:choose>
											<c:when test="${recruitBoard.recruitPosition eq '경영지원'}">
												<c:set var="imageFileName" value="administration.png"/>
											</c:when>
											<c:when test="${recruitBoard.recruitPosition eq '마케팅'}">
												<c:set var="imageFileName" value="marketing.png"/>
											</c:when>
											<c:when test="${recruitBoard.recruitPosition eq '연구개발'}">
												<c:set var="imageFileName" value="rnd.png"/>
											</c:when>
											<c:when test="${recruitBoard.recruitPosition eq '생산'}">
												<c:set var="imageFileName" value="produce.png"/>
											</c:when>
											<c:otherwise>
												<c:set var="imageFileName" value="dummy.jpg"/>
											</c:otherwise>
										</c:choose>
										<div class="post-img mb-2" style="height: 150px; overflow: hidden;">
											<img src="${pageContext.request.contextPath}/resources/recruitImages/${imageFileName}"
											alt="Thumbnail" class="img-fluid rounded" style="object-fit: cover; height: 100%; width: 100%;">
										</div>
										
										<!-- 제목 -->
										<h2 class="title fs-6 text-truncate mt-2 mb-2" title="${recruitBoard.recruitTitle}" style="margin-bottom: 2rem;">
											<a style="color: black;"
											href="${pageContext.request.contextPath}/homepage/recruit/detail?recruitId=${recruitBoard.recruitId}">
												<strong>${recruitBoard.recruitTitle}</strong> 
											</a>
										</h2>
										
										<!-- 메타 정보 -->
										<div class="meta-top" style="margin-bottom: 0.75rem;">
											<ul class="list-unstyled">
												<li><i class="bi bi-building mb-1"></i> 모집부서 : ${recruitBoard.recruitPosition}</li>
												<li><i class="bi bi-briefcase mb-1"></i> 고용형태 : ${recruitBoard.recruitHiretype}</li>
												<li>
													<i class="bi bi-calendar-range mb-1"></i> 모집기간 : 
													<fmt:formatDate value="${recruitBoard.recruitStartdate}" pattern="yyyy-MM-dd" />
													 ~ <fmt:formatDate value="${recruitBoard.recruitEnddate}" pattern="yyyy-MM-dd" />
												</li>
											</ul>
										</div>
									
										<!-- d-day & 상세보기버튼 -->
										<div class="read-more mt-auto d-flex justify-content-between align-items-center">
											<span class="d-day badge bg-warning text-white"
											style="font-size: 1rem; font-weight: bold;">
											</span>
											<a class="btn btn-sm btn-outline-primary"
											   href="${pageContext.request.contextPath}/homepage/recruit/detail?recruitId=${recruitBoard.recruitId}">
												상세보기
											</a>
										</div>
									
									  </article>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="col-12">
									<p class="text-muted">공고가 존재하지 않습니다.</p>
								</div>
							</c:otherwise>
						</c:choose>
					</div> <!-- article end  -->
				</div><!-- container end  -->
				
			</div><!-- card body end -->
		</div><!-- card end -->
<%-- ${recruitBoardList } --%>
	</section>
</div>

 
 <script>
document.addEventListener('DOMContentLoaded', function () {

  const today = new Date();
  const currentYear = today.getFullYear();
  const currentMonth = today.getMonth() + 1;
  const currentHalf = currentMonth <= 6 ? "상반기" : "하반기";
  document.getElementById("boardTitle").innerText = currentYear + "년 " + currentHalf + " 채용공고";

  function filterRecruit() {
    const selectedYear = document.getElementById('yearSelect').value;
    const selectedHalf = document.getElementById('halfSelect').value;

    const cardsContainer = document.querySelector('.row.gx-5.gy-5'); // 카드들이 들어있는 컨테이너
    const cards = Array.from(cardsContainer.querySelectorAll('.article-card'));

    const today3 = new Date();
    today3.setHours(0, 0, 0, 0); // 비교 시 시각 제거

    const visibleCards = []; // 화면에 보여질 카드 + D-Day 값 저장용 배열

    cards.forEach(card => {
      const startDate = new Date(card.getAttribute('data-startdate'));
      const endDate = new Date(card.getAttribute('data-enddate'));
      endDate.setHours(0, 0, 0, 0);

      const cardYear = startDate.getFullYear();
      const cardMonth = startDate.getMonth() + 1;

      // 반기 조건 체크
      const isInHalf =
        (selectedHalf === "상" && cardMonth >= 1 && cardMonth <= 6) ||
        (selectedHalf === "하" && cardMonth >= 7 && cardMonth <= 12);

      if (cardYear == selectedYear && isInHalf) {
        card.classList.remove('d-none');

        // 조건을 만족한 카드는 배열에 D-Day 값과 함께 저장
        visibleCards.push({
          element: card,
          dDay: Math.floor((endDate - today3) / (1000 * 60 * 60 * 24))
        });
      } else {
        card.classList.add('d-none');
      }

      // D-Day 뱃지 표시 처리
      const dDayElement = card.querySelector('.d-day');
      const dayDiff = Math.floor((endDate - today3) / (1000 * 60 * 60 * 24));

      if (dDayElement) {
        dDayElement.classList.remove('bg-danger', 'bg-secondary'); // 기존 클래스 제거
        dDayElement.classList.add('bg-warning'); // 기본 배경색

        if (dayDiff > 0) {
          dDayElement.textContent = `D-\${dayDiff}`;
        } else if (dayDiff === 0) {
          dDayElement.textContent = `D-Day`;
          dDayElement.classList.replace('bg-warning', 'bg-danger');
        } else {
          dDayElement.textContent = `마감`;
          dDayElement.classList.replace('bg-warning', 'bg-secondary');
        }
      }
    });

 	// D-Day 오름차순 & 마감된 공고는 뒤로
    visibleCards.sort((a, b) => {
      if (a.dDay < 0 && b.dDay >= 0) return 1;
      if (a.dDay >= 0 && b.dDay < 0) return -1;
      return a.dDay - b.dDay;
    });

    visibleCards.forEach(item => {
      cardsContainer.appendChild(item.element);
    });
    
  }

  // 버튼 클릭 시 필터 실행
  document.getElementById('searchButton').addEventListener('click', filterRecruit);
  filterRecruit(); // 초기 필터 적용

});
</script>
 





