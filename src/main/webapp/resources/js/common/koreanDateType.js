/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 4. 11.     	CHOI            날짜 변환 타입 모듈 
 *
 * </pre>
 */

// 한국식 날짜 포맷팅 -> 시 분 까지 나옴 
export function formatKoreanDateTime(dateString) {
  if (!dateString) return "";
  // "YYYY-MM-DD HH:mm:ss" 형식의 문자열이라 가정하고 " " -> "T" 로 치환
  const fixedString = dateString.replace(" ", "T");
  const date = new Date(fixedString);
  if (isNaN(date.getTime())) {
    return dateString;
  }
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");
  const hour = String(date.getHours()).padStart(2, "0");
  const minute = String(date.getMinutes()).padStart(2, "0");
  return `${year}년 ${month}월 ${day}일 ${hour}시 ${minute}분`;
}

/**
 * 정규 포맷 
 * 날짜 포맷 변환 (YYYY-MM-DD HH:mm 형식)
 * @param {String} dateString - 날짜 문자열
 * @returns {String} 포맷된 날짜 문자열
 */
export function formatEngDate(dateString) {
    if (!dateString) return "-";
    let date = new Date(dateString);
    if (isNaN(date.getTime())) {
        console.error("Invalid date format:", dateString);
        return "-";
    }
    let year = date.getFullYear();
    let month = ('0' + (date.getMonth() + 1)).slice(-2);
    let day = ('0' + date.getDate()).slice(-2);
    let hours = ('0' + date.getHours()).slice(-2);
    let minutes = ('0' + date.getMinutes()).slice(-2);
    return `${year}-${month}-${day} ${hours}:${minutes}`;
}





