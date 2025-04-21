package kr.or.ddit.signature.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.security.SecurityUtil;
import kr.or.ddit.signature.service.SignatureService;
import kr.or.ddit.signature.vo.SignatureVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SignatureController {

    @Autowired
    private SignatureService service;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/signature/registerForm")
    public String registerForm(Model model) {
        AccountVO account = SecurityUtil.getrealUser();
        String empId = account.getEmpId();

        SignatureVO existingSign = service.getSignature(empId);
        model.addAttribute("empId", empId);
        model.addAttribute("existingSign", existingSign);

        return "tiles:signature/signatureRegister";
    }

    @PostMapping("/signature/register")
    public String register(@RequestParam("signImage") MultipartFile signImage,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        AccountVO account = SecurityUtil.getrealUser();
        String empId = account.getEmpId();

        SignatureVO existingSign = service.getSignature(empId);

        if (signImage.isEmpty()) {
            model.addAttribute("errorMsg", "서명 이미지를 선택해야 합니다.");
            return "tiles:signature/signatureRegister";
        }

        String uploadDir = servletContext.getRealPath("/resources/signatures/");
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String extension = signImage.getOriginalFilename().substring(signImage.getOriginalFilename().lastIndexOf("."));
        String fileName = empId + "_" + UUID.randomUUID().toString() + extension;
        String filePath = uploadDir + File.separator + fileName;

        try {
            File file = new File(filePath);
            signImage.transferTo(file);

            // 기존 이미지 삭제
            if (existingSign != null && existingSign.getSignImagePath() != null) {
                File oldFile = new File(servletContext.getRealPath(existingSign.getSignImagePath()));
                if (oldFile.exists()) oldFile.delete();
            }

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", "파일 업로드 중 오류가 발생했습니다.");
            return "tiles:signature/signatureRegister";
        }

        String webPath = "/resources/signatures/" + fileName;

        SignatureVO sign = new SignatureVO();
        sign.setEmpId(empId);
        sign.setSignImagePath(webPath);

        if (existingSign == null) {
            service.insertSign(sign);
            redirectAttributes.addFlashAttribute("successMsg", "서명이 정상적으로 등록되었습니다.");
        } else {
            service.updateSign(sign);
            redirectAttributes.addFlashAttribute("successMsg", "서명이 성공적으로 수정되었습니다.");
        }

        return "redirect:/signature/registerForm";
    }
}
