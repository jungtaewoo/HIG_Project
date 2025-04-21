<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 4. 9.     	KHS            최초 생성
 *
-->
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Ag-Grid Basic Example</title>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <style type="text/tailwindcss">
      .e7e {
        @apply text-center text-3xl my-1 pb-4 rounded bg-blue-500 px-4 py-2 font-bold text-white hover:bg-blue-600;
      }
      /* 헤더 텍스트 가운데 정렬 위함 */
      .centered {
        .ag-header-cell-label {
          justify-content: center !important;
        }
      }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/ag-grid-community/dist/ag-grid-community.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.8.4/axios.min.js"></script>
    <link rel="stylesheet" href="index.css" />
  </head>

  <body>
    <h1 class="e7e">대덕 에스파 출동</h1>
    <!-- 테마는 quartz, balham, material,alpine 있음 -->
    <div id="e7eGrid" class="ag-theme-quartz-dark w-full h-100"></div>
    <button
      class="mt-3 rounded-[15%] border-2 bg-slate-700 text-white"
      onclick="fExp()"
    >
      내보내깅
    </button>
    <script type="module">
      // locale 한국어 import
      import { AG_GRID_LOCALE_KR } from "https://cdn.jsdelivr.net/npm/@ag-grid-community/locale@33.2.1/+esm";

      // 커스텀 CellRenderer 함수 만들기, 간단히 name과 isMz에 대해서만
      function nameRenderer(objInfo) {
        //console.log("체킁:", objInfo);
        let color = objInfo.data.isMZ ? "yellow" : "#ED9247FF";
        let weight = objInfo.data.isMZ ? "bolder" : "normal";
        return `
          <span style="color:${color};font-weight:${weight}" >
            <img style="display:inline-block"
              src="https://api.dicebear.com/9.x/big-smile/svg?seed=${objInfo.value}"
              width=25 height=25  alt="avatar" />
            ${objInfo.value}
          </span>`;
      }

      function mzRenderer(objInfo) {
        return `
          <input type=checkbox ${objInfo.value ? "checked" : ""}
        `;
      }

      // 컬럼 정의
      const columnDefs = [
        {
          field: "name",
          headerName: "이름",
          cellRenderer: nameRenderer,
        },
        {
          field: "birth",
          headerName: "생일",
          valueFormatter: (objInfo) => {
            //console.log("체로롱:", typeof objInfo.value);
            if (typeof objInfo.value == "object") {
              return objInfo.value.toISOString().split("T")[0];
            }
            return objInfo.value;
          },
          cellEditor: "agDateCellEditor",
        },
        {
          field: "nationality",
          headerName: "국적",
          cellEditor: "agSelectCellEditor", // 추가
          cellEditorParams: {
            // 선택사항 내맘대로 추강
            values: [
              "한국",
              "미국",
              "중국",
              "일본",
              "슬로바키아",
              /* 생략 */
            ],
          },
        },
        {
          field: "special",
          headerName: "담당",
          cellEditor: "agSelectCellEditor", // 추가
          cellEditorParams: {
            // 선택사항 내맘대로 추강
            values: [
              "기획",
              "비동기",
              "리액트",
              "JSP",
              "바닐라JS",
              "마이바티스",
              "파이터",
              "사격",
              "망토펴기",
              "어리둥절",
              "댄스폭격",
              /* 생략 */
            ],
          },
        },
        {
          field: "isMZ",
          headerName: "엠지?",
          valueFormatter: (objInfo) => {
            return objInfo.value;
          },
          cellRenderer: mzRenderer,
          cellEditor: "agCheckboxCellEditor",
        },
      ];

      // 일단 빈 데이터 설정(초기값), 설정 안하면 주구창창 로딩중 메세지
      const rowData = [];

      // 설정 옵션: 중요, 위에 정의한 것들이 여기서 통합됨에 주목
      const gridOptions = {
        localeText: AG_GRID_LOCALE_KR, // 한국어로
        theme: "legacy", // 요걸 주어야 div에 준 theme class명이 동작
        columnDefs: columnDefs,
        rowData: rowData,
        defaultColDef: {
          flex: 1, // 자동으로 같은 사이즈롱
          filter: true,
          floatingFilter: true, // 검색 입력창 별도 row
          editable: true, // 수정 가능
          minWidth: 100,
          headerClass: "centered",
        },
        rowHeight: 45, // row 높이지정
        // 페이지 설정
        pagination: true,
        //paginationAutoPageSize:true,  // 요게 열려있으면 아래껀 무시당함!
        paginationPageSizeSelector: [5, 10, 20], // 원하는 페이지 수 나열
        paginationPageSize: 10, // 위에 꺼 중 하나를 선택
        onCellClicked: (params) => {
          console.log("cell was clicked", params);
        },
      };

      function getData() {
        // axios를 이용한 AJAX
        axios.get("dummyData.json").then((response) => {
          let rslt = response.data;
          console.log("rslt", rslt);
          gridApi.updateGridOptions({ rowData: rslt });
        });

        /* 바닐라 JS를 이용한 AJAX
        var xhr = new XMLHttpRequest();
        xhr.open("get", "dummyData.json", true);
        xhr.onreadystatechange = function () {
          if (xhr.readyState == 4 && xhr.status == 200) {
            // 데이타 구조 억지 땜빵!
            let rslt = JSON.parse(xhr.responseText);
            console.log("체킁:", rslt);
            gridApi.setGridOption("rowData", rslt); //아래 라인과 동일한 의미
            //gridApi.updateGridOptions({ rowData: rslt });
          }
        };
        xhr.send();
        */

        //  jQuery를 이용한 AJAX
        $.ajax({
          method: "get",
          url: "dummyData.json",
          dataType: "json",
          success: (rslt) => {
            gridApi.updateGridOptions({ rowData: rslt });
          },
        });


        /*fetch를 이용한 AJAX
        fetch("dummyData.json").then((response) => {
          response.json().then((rslt) => {
            gridApi.setGridOption("rowData", rslt);
          });
        });
        */
      }

      // csv 내보내기, 별로 안 중요, 그냥 이대로 설정하고 값만 고치면 됨
      function fExp() {
        var v_params = {
          suppressQuotes: "true", // none, true
          columnSeparator: "   ", // default값이 ,(콤마)
          customHeader: "이름 축하요 국적 담당 엠지", // 헤더명 추가 출력
          customFooter: "이거슨 푸터", // 데이타 아래에 footer추가
        };
        gridApi.exportDataAsCsv(v_params);
      }

      // 그리드 셋업

      let gridApi;
      document.addEventListener("DOMContentLoaded", () => {
        const gridDiv = document.querySelector("#e7eGrid");
        // new agGrid.Grid(gridDiv, gridOptions);  // deprecated
        gridApi = agGrid.createGrid(gridDiv, gridOptions);
        getData(); // 데이터 불러오깅

        // 컬럼 속성 동적으로 바꾸깅
        const columnDefs = gridApi.getColumnDefs(); // 현재 컬럼정의 가져오깅(당근 배열임)
        for (let colDef of columnDefs) {
          if (colDef.headerName == "생일") {
            colDef.headerName = "축하요";
            break;
          }
        }

        gridApi.setGridOption("columnDefs", columnDefs);
      });
    </script>
  </body>
</html>