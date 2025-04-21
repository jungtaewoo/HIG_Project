<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 4. 7.     	KHS            최초 생성
 *  2025. 4. 11.		KHT			   상단 타이틀 추가
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 
<div class="page-title dark-background" data-aos="fade" style="background-color:#000; padding: 45px;">
  <div class="container position-relative">
    헤더 내용이 들어갑니다
  </div>
</div>
 -->

<!-- 페이지 상단 타이틀 추가 -->
<div class="page-title dark-background" data-aos="fade" 
style="background-image: url('${pageContext.request.contextPath}/resources/BizPage/assets/img/page-title-bg.jpg');">
  <div class="container position-relative">
    <h1>대덕우리전자</h1>
    <nav class="breadcrumbs">
      <ol>
        <li><a href="${pageContext.request.contextPath }/home">Home</a></li>
        <li class="current">About</li>
      </ol>
    </nav>
  </div>
</div>


<section id="about" class="about section">
  <!-- Section Title -->
  <div class="container section-title" data-aos="fade-up">
    <h2>회사 소개</h2>
    <p>대한민국 전자 산업의 미래, 대덕우리전자입니다.</p>
  </div><!-- End Section Title -->

  <div class="container">
    <div class="row gy-4">
      <div class="col-lg-6 content" data-aos="fade-up" data-aos-delay="100">
        <p class="who-we-are">Who We Are</p>
        <h3>첨단 기술과 신뢰로 전자산업을 선도합니다</h3>
        <p class="fst-italic">
          대덕우리전자는 반도체, 통신장비, 임베디드 시스템 등 다양한 분야의 전자 솔루션을 개발 및 공급하며
          고객과 함께 성장하는 글로벌 기업입니다.
        </p>
        <ul>
          <li><i class="bi bi-check-circle"></i> <span>1999년 창립, 20년 이상의 전자기술 개발 경험</span></li>
          <li><i class="bi bi-check-circle"></i> <span>국내외 50여 개 기업과 협력</span></li>
          <li><i class="bi bi-check-circle"></i> <span>ISO 인증 및 다수의 기술 특허 보유</span></li>
        </ul>
        <a href="${pageContext.request.contextPath}/about/vision" class="read-more">
          <span>자세히 보기</span><i class="bi bi-arrow-right"></i>
        </a>
      </div>

      <div class="col-lg-6 about-images" data-aos="fade-up" data-aos-delay="200">
        <div class="row gy-4">
          <div class="col-lg-6">
            <img src="${pageContext.request.contextPath}/resources/BizPage/assets/img/factory/DeFactory.jpg" class="img-fluid" alt="대덕우리전자 사업장">
          </div>
          <div class="col-lg-6">
            <div class="row gy-4">
              <div class="col-lg-12">
                <img src="${pageContext.request.contextPath}/resources/BizPage/assets/img/factory/DeR&D.jpg" class="img-fluid" alt="연구 개발 현장">
              </div>
              <div class="col-lg-12">
                <img src="${pageContext.request.contextPath}/resources/BizPage/assets/img/factory/DeR&D2.jpg" class="img-fluid" alt="생산 라인">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
