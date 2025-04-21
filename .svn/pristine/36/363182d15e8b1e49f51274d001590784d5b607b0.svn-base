package kr.or.ddit.employee.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.annual.service.AnnualService;
import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.validate.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/employee/empFormUI")
public class EmpInsertController {
	
	public static final String MODELNAME = "employee";
	
    @Autowired
    private FileInfoService fileService;

	@Inject
	private EmployeeService service;
	
	@Autowired
	private AnnualService Aservice;
	
	@GetMapping
	public String formUI() {
		return "tiles:/employee/empFormUI";
	}

	@PostMapping
	public String formProcess(
			@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) EmployeeVO employee
			,BindingResult errors
			,RedirectAttributes redirectAttributes
			, MultipartFile file
		) throws IOException { 
		
		String logicalName = null;
		boolean valid = !errors.hasErrors();
		
		Long fileId = null;  // 그룹화된 파일 ID

        if (file != null && file.getSize() > 0) {
            
                if (!file.isEmpty()) {
                    if (fileId == null) {
                        fileId = fileService.createFileGroup(); // 새로운 FILE_ID 생성
                    }
                    Long detailId = fileService.saveFileWithGroup(file, fileId);
                    fileId = detailId;
                }
        }
		
		if (valid) {
			employee.setEmpImg(fileId);
			service.createEmp(employee);
			log.info("asdasd되냐");
			logicalName = "redirect:/employee/empList";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, employee);
			String errorName = BindingResult.MODEL_KEY_PREFIX + MODELNAME;
			redirectAttributes.addFlashAttribute(errorName, errors);

			logicalName = "redirect:/employee/empFormUI";
		}

		return logicalName;
	}

}
