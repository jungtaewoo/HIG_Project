(function($, Backbone, _) {
  // PlusMinusRow 함수 정의
  window.plusMinusRow = function (options) {
    // 기본 옵션 설정
    var defaults = {
      maxRow: 0,           // 0: 무제한
      copyRowNoSize: 1     // 순번 증가량
    };

    // 사용자가 전달한 옵션과 기본 옵션 병합
    var settings = $.extend({}, defaults, options);

    // 행 추가 수행 횟수 계산 (수정 시 고려)
    var plusCnt;
    if ($("#" + settings.tableId + " .copiedRow")[0] === undefined) {
      plusCnt = 1;
    } else {
      if (!$($("#" + settings.tableId + " ." + settings.copyRowClass + " td")[0]).attr("rowspan")) {
        plusCnt = $("#" + settings.tableId + " .copiedRow").length + 1;
      } else {
        var rowCnt = parseInt($($("#" + settings.tableId + " ." + settings.copyRowClass + " td")[0]).attr("rowspan"));
        plusCnt = ($("#" + settings.tableId + " .copiedRow").length + rowCnt) / rowCnt;
      }
    }

    // 행 추가 버튼 클릭 이벤트
    $("#" + settings.plusBtnId).on('click', function () {
      var currentRows = $("#" + settings.tableId + " ." + settings.copyRowClass).length +
                        $("#" + settings.tableId + " .copiedRow").length;
      if (currentRows < settings.maxRow || settings.maxRow == 0) {
        if (settings.maxNo !== undefined) {
          if (parseInt($("#" + settings.tableId + " ." + settings.copyRowNoClass + ":last").text()) < settings.maxNo) {
            plusRow();
            plusCnt++;
          }
        } else {
          plusRow();
          plusCnt++;
        }
      }
    });

    // 행 삭제 버튼 클릭 이벤트
    $("#" + settings.minusBtnId).on('click', minusRow);

    function plusRow() {
      // 템플릿 행(clone)
      var $tr = $("#" + settings.tableId + " ." + settings.copyRowClass).clone(true);

      // (옵션) rowspan 처리
      if ($("#" + settings.tableId + " ." + settings.rowspanClass)[0] !== undefined) {
        $.each($("#" + settings.tableId + " ." + settings.rowspanClass), function (k, v) {
          $(v).attr("rowspan", parseInt($(v).attr("rowspan")) + $tr.length);
        });
        $.each($tr.find("td[rowspan]"), function (k, v) {
          if ($(v).hasClass(settings.rowspanClass)) {
            $(v).remove();
          }
        });
      }

      // (옵션) 순번(No) 처리
      if ($("#" + settings.tableId + " ." + settings.copyRowNoClass)[0] !== undefined) {
        var copyRowNoCnt = $tr.find("." + settings.copyRowNoClass).length;
        for (var i = 0; i < copyRowNoCnt; i++) {
          var newNo;
          if (!$tr.find("." + settings.copyRowNoClass).attr('rowspan')) {
            newNo = parseInt($($tr.find("." + settings.copyRowNoClass)[i]).text()) +
                    settings.copyRowNoSize * plusCnt * $tr.length;
          } else {
            newNo = parseInt($($tr.find("." + settings.copyRowNoClass)[i]).text()) +
                    settings.copyRowNoSize * plusCnt;
          }
          $($tr.find("." + settings.copyRowNoClass)[i]).text(newNo);
        }
      }

      // 컴포넌트 초기화 및 클래스 변경
      var i = 1;
      $.each($tr, function (k, v) {
        $(v).removeClass(settings.copyRowClass);
        $(v).addClass('copiedRow');
        initComponent($(v), i++);
      });

      // 테이블에 새 행 추가 (마지막 행 뒤)
      if ($("#" + settings.tableId + " .copiedRow")[0] === undefined) {
        $("#" + settings.tableId + " ." + settings.copyRowClass + ":last").after($tr);
      } else {
        $("#" + settings.tableId + " .copiedRow:last").after($tr);
      }

      // 행 추가 후 콜백 실행
      if (typeof settings.plusRowCallback == 'function') {
        settings.plusRowCallback(this);
      }
    }

    function initComponent($tr, i) {
      var editorFormCnt = 1;
      var radioName = "";
      var checkName = "";
      $.each($tr.find("td input"), function (k, v) {
        var componentType = $(v).attr("data-dsl");
        var componentId = $(v).attr("id");
        if (!(componentType.search("check") > -1) && !(componentType.search("radio") > -1)) {
          var newId = settings.tableId + "_" + ($("#" + settings.tableId).find(".copiedRow").length + i) + "_" + editorFormCnt;
          $(v).attr({name: newId, id: newId});
          $(v).val("");
          if (componentType.search("currency") > -1) {
            var parseKey;
            componentType.replace(/{{([^}}]*)}}/g, function (m, key) {
              parseKey = key;
            });
            var precision = parseKey.split('_');
            $(v).inputmask({
              'alias': 'decimal',
              'groupSeparator': ',',
              'autoGroup': true,
              'digits': parseInt(precision[1] ? precision[1] : '0'),
              'allowMinus': true
            });
          } else if (componentType.search("calendar") > -1) {
            $(v).datepicker("destroy").removeClass('hasDatepicker');
            $(v).datepicker({
              dateFormat: "yy-mm-dd(D)",
              changeMonth: true,
              changeYear: true,
              yearSuffix: ""
            });
          }
        } else if (componentType.search("radio") > -1) {
          if ($(v).attr('name') == radioName) {
            editorFormCnt--;
          } else {
            radioName = $(v).attr('name');
          }
          var newName = settings.tableId + "_" + ($("#" + settings.tableId).find(".copiedRow").length + i) + "_" + editorFormCnt;
          var newId = settings.tableId + "_" + ($("#" + settings.tableId).find(".copiedRow").length + i) + "_" + editorFormCnt + "_" + componentId.split("_")[2];
          $(v).attr({name: newName, id: newId, checked: false});
        } else if (componentType.search("check") > -1) {
          var curCheckName = $(v).attr('name').split("_")[0] + "_" + $(v).attr('name').split("_")[1];
          if (curCheckName == checkName) {
            editorFormCnt--;
          } else {
            checkName = curCheckName;
          }
          var newId = settings.tableId + "_" + ($("#" + settings.tableId).find(".copiedRow").length + i) + "_" + editorFormCnt + "_" + componentId.split("_")[2];
          $(v).attr({name: newId, id: newId, checked: false});
        }
        editorFormCnt++;
      });

      $.each($tr.find("td select"), function (k, v) {
        var newName = settings.tableId + "_" + ($("#" + settings.tableId).find(".copiedRow").length + i) + "_" + editorFormCnt;
        $(v).attr({name: newName, id: newName});
        editorFormCnt++;
      });

      $.each($tr.find("td textarea"), function (k, v) {
        var newId = settings.tableId + "_" + ($("#" + settings.tableId).find(".copiedRow").length + i) + "_" + editorFormCnt;
        $(v).attr({name: newId, id: newId});
        $(v).val("");
        editorFormCnt++;
      });

      return $tr;
    }

    function minusRow() {
      if ($("#" + settings.tableId + " .copiedRow")[0] !== undefined) {
        if ($("#" + settings.tableId + " ." + settings.rowspanClass)[0] !== undefined) {
          $.each($("#" + settings.tableId + " ." + settings.rowspanClass), function (k, v) {
            $(v).attr("rowspan", parseInt($(v).attr("rowspan")) - $("#" + settings.tableId + " ." + settings.copyRowClass).length);
          });
        }
        for (var i = 0; i < $('.' + settings.copyRowClass).length; i++) {
          $("#" + settings.tableId + " .copiedRow:last").remove();
        }
        plusCnt--;
      }
      if (typeof settings.minusRowCallback == 'function') {
        settings.minusRowCallback(this);
      }
    }
  };

  // Backbone Integration 뷰 정의
  var Integration = Backbone.View.extend({
    initialize: function(options) {
      this.options = options || {};
      this.docModel = this.options.docModel;
      this.variables = this.options.variables;
      this.infoData = this.options.infoData;
    },
    render: function() {
      var self = this;
      // 모달 내부에 동적 테이블이 로드된 후 실행되어야 하므로,
      // 예시로 viewModeHiddenPart 클래스가 있으면 보이게 처리
      $('.viewModeHiddenPart').show();
      // 계산 관련 이벤트 예시 (필요 시 구현)
      $(".amount input").on("change", function(){ self.calAmount(); });
      $(".price input").on("change", function(){ self.calAmount(); });
      
      PlusMinusRow({
        tableId: "dynamic_table1",      // 동적 행 추가 대상 테이블 id
        plusBtnId: "plus1",             // 행 추가 버튼 id
        minusBtnId: "minus1",           // 행 삭제 버튼 id
        copyRowClass: "copyRow1",       // 복사할 행의 클래스
        copyRowNoClass: "copyRowNo1",   // (옵션) 순번 열 클래스
        rowspanClass: "rowspanTr1",     // (옵션) rowspan 처리 대상 클래스
        minusRowCallback: function() { self.calAmount(); },
        plusRowCallback: function() { self.calAmount(); }
      });
    },
    calAmount: function() {
      var self = this;
      var cur = 0;
      var sum_cur = 0;
      $("#dynamic_table1 tr").each(function(i, e) {
        if ($(e).find('.amount')[0]) {
          var amount = parseFloat($(e).find('.amount input').val().replace(/\,/g, ""));
          var price = parseFloat($(e).find('.price input').val().replace(/\,/g, ""));
          if (isNaN(amount)) amount = 0;
          if (isNaN(price)) price = 0;
          cur = parseFloat((amount * price).toFixed(2));
          $(e).find(".cur").text(self._convertCurrencyFormat(cur));
          sum_cur = parseFloat((sum_cur + cur).toFixed(2));
        }
      });
      $(".sum_cur").text(self._convertCurrencyFormat(sum_cur));
    },
    _convertCurrencyFormat: function(value) {
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    renderViewMode: function() { $('.viewModeHiddenPart').hide(); },
    onEditDocument: function() { this.render(); },
    beforeSave: function() { $('.viewModeHiddenPart').hide(); },
    afterSave: function() { $('.viewModeHiddenPart').hide(); },
    validate: function() { return true; },
    getDocVariables: function() {}
  });
  
  // DOMContentLoaded 이벤트 후, 모달이 열릴 때 Integration 뷰 초기화
  document.addEventListener("DOMContentLoaded", function() {
    // 모달이 열릴 때 초기화하기 위해 "shown.bs.modal" 이벤트를 사용
    $('#approvalFormModal').on('shown.bs.modal', function() {
      var integrationView = new Integration();
      integrationView.render();
    });
  });
  
  // 모듈이 Backbone View를 반환하도록 설정
  return Integration;
  
})(jQuery, Backbone, _);
