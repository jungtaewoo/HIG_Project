<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 4. 8.     	KHS            최초 생성
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
    <h1>서비스</h1>
    <nav class="breadcrumbs">
      <ol>
        <li><a href="${pageContext.request.contextPath }/home">Home</a></li>
        <li class="current">Service</li>
      </ol>
    </nav>
  </div>
</div>

<section id="services" class="services section">
  <!-- Section Title -->
  <div class="container section-title" data-aos="fade-up">
    <h2>서비스 안내</h2>
    <p>대덕 우리전자의 주요 서비스와 솔루션을 확인해 보세요.</p>
  </div><!-- End Section Title -->

  <div class="container">
    <div class="row gy-4">

      <!-- 서비스 항목 1: 제품 상담 및 견적 -->
      <div class="col-md-6" data-aos="fade-up" data-aos-delay="100">
        <div class="service-item d-flex position-relative h-100">
          <i class="bi bi-chat-square-text icon flex-shrink-0"></i>
          <div>
            <h4 class="title"><a href="service-details.html" class="stretched-link">제품 상담 및 견적</a></h4>
            <p class="description">
              고객님의 니즈에 맞춘 최적의 전자제품 및 컴퓨터 솔루션을 제안합니다. 제품 상담 후 빠른 견적을 통해 효율적인 구매 결정을 도와드립니다.
            </p>
          </div>
        </div>
      </div><!-- End Service Item -->

	     <!-- 서비스 항목 2: 혁신 전자제품 솔루션 -->
		<div class="col-md-6" data-aos="fade-up" data-aos-delay="200">
		  <div class="service-item d-flex position-relative h-100">
		    <i class="bi bi-cpu icon flex-shrink-0"></i>
		    <div>
		      <h4 class="title">
		        <a href="service-details.html" class="stretched-link">혁신 전자제품 솔루션</a>
		      </h4>
		      <p class="description">
		        당사는 컴퓨터, 스마트폰, TV 등 최신 전자제품을 직접 설계, 개발 및 생산합니다.
		        혁신적인 기술과 세련된 디자인을 결합하여, 고객의 생활을 보다 편리하고 스마트하게 만들어드립니다.
		        고성능과 안정성을 바탕으로 한 제품을 통해 항상 미래를 선도하는 솔루션을 제공해 드립니다.
		      </p>
		    </div>
		  </div>
		</div><!-- End Service Item -->


      <!-- 서비스 항목 3: 전자제품 판매 및 A/S -->
      <div class="col-md-6" data-aos="fade-up" data-aos-delay="300">
        <div class="service-item d-flex position-relative h-100">
          <i class="bi bi-shop icon flex-shrink-0"></i>
          <div>
            <h4 class="title"><a href="service-details.html" class="stretched-link">전자제품 판매 및 A/S</a></h4>
            <p class="description">
              최신 컴퓨터 및 전자제품을 합리적인 가격에 제공하며, 전문 A/S 센터를 통한 사후 관리를 실시합니다. 제품 구매 후에도 안심할 수 있는 서비스를 약속드립니다.
            </p>
          </div>
        </div>
      </div><!-- End Service Item -->

      <!-- 서비스 항목 4: 기술 지원 및 컨설팅 -->
      <div class="col-md-6" data-aos="fade-up" data-aos-delay="400">
        <div class="service-item d-flex position-relative h-100">
          <i class="bi bi-tools icon flex-shrink-0"></i>
          <div>
            <h4 class="title"><a href="service-details.html" class="stretched-link">기술 지원 및 컨설팅</a></h4>
            <p class="description">
              전문 기술진이 현장에서 신속하게 문제를 해결하며, 시스템 통합 및 IT 인프라 구축에 대한 컨설팅 서비스를 제공합니다.
            </p>
          </div>
        </div>
      </div><!-- End Service Item -->

      <!-- 서비스 항목 5: 대량 구매 및 B2B 솔루션 -->
      <div class="col-md-6" data-aos="fade-up" data-aos-delay="500">
        <div class="service-item d-flex position-relative h-100">
          <i class="bi bi-people icon flex-shrink-0"></i>
          <div>
            <h4 class="title"><a href="service-details.html" class="stretched-link">대량 구매 및 B2B 솔루션</a></h4>
            <p class="description">
              기업 및 기관 고객님들을 위한 맞춤형 대량 구매 혜택과 B2B 전자상거래 솔루션을 제공하여 비용 절감과 효율적인 운영을 지원합니다.
            </p>
          </div>
        </div>
      </div><!-- End Service Item -->

      <!-- 서비스 항목 6: 시스템 통합 및 IT 컨설팅 -->
      <div class="col-md-6" data-aos="fade-up" data-aos-delay="600">
        <div class="service-item d-flex position-relative h-100">
          <i class="bi bi-display icon flex-shrink-0"></i>
          <div>
            <h4 class="title"><a href="service-details.html" class="stretched-link">시스템 통합 및 IT 컨설팅</a></h4>
            <p class="description">
              최신 IT 솔루션과 전자제품을 기반으로 고객사의 업무 효율성을 극대화할 수 있는 시스템 통합 및 컨설팅 서비스를 제공합니다.
            </p>
          </div>
        </div>
      </div><!-- End Service Item -->

    </div>
  </div>
</section>