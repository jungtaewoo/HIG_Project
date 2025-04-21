package kr.or.ddit.spring.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * 급여명세서 생성, 암호화
 * 이미지 pdf변환
 * 
 * @author youngjun
 * @since 2025. 4. 3.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 4. 3.     	youngjun	          최초 생성
 *
 * </pre>
 */
public class EmailSalaryUtil {

	public static void generatePasswordPdf(String imagePath, String pdfPath, String password) throws IOException {
	    PDDocument document = new PDDocument();

	    PDPage page = new PDPage(PDRectangle.A4);
	    document.addPage(page);

	    PDImageXObject PDIimage = PDImageXObject.createFromFile(imagePath, document);
	    PDPageContentStream contentStream = new PDPageContentStream(document, page);

	    float imgWidth = page.getMediaBox().getWidth() - 100;
	    float scale = imgWidth / PDIimage.getWidth();
	    float imgHeight = PDIimage.getHeight() * scale;

	    contentStream.drawImage(PDIimage, 50, page.getMediaBox().getHeight() - imgHeight - 50, imgWidth, imgHeight);
	    contentStream.close();

	    // 암호 설정
	    AccessPermission ap = new AccessPermission();
	    StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, ap);
	    spp.setEncryptionKeyLength(128);
	    spp.setPermissions(ap);
	    document.protect(spp);

	    //  PDF 저장
	    document.save(pdfPath);

	    // 파일 닫기
	    document.close();
	}

	
	/**
	 * 이메일pdf 파일첨부, 발송
	 */
	public static void sendPdfEmail(String to, String subject, String body, String pdfPath)throws MessagingException {
		//SMTP설정
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		final String username ="honeynut7789@gmail.com";
		final String password ="icqi jmax ovzf pibx";
		
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
		
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        
        //본문
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(body);
        
        //첨부파일
        MimeBodyPart attachPart = new MimeBodyPart();
        DataSource source = new FileDataSource(pdfPath);
        attachPart.setDataHandler(new DataHandler(source));
        attachPart.setFileName(new File(pdfPath).getName());
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachPart);
        
        message.setContent(multipart);
        Transport.send(message);
	}
	

}
