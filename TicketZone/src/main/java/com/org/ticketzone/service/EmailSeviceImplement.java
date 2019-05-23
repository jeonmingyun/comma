package com.org.ticketzone.service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.EmailVO;

@Service
public class EmailSeviceImplement implements EmailService {
	@Inject
	JavaMailSender mailSender;//메일 발송 객체
	
	@Override
	public void sendMail(EmailVO vo) {
		try{
			MimeMessage msg=mailSender.createMimeMessage();
			//이메일 수신자
			msg.addRecipient(RecipientType.TO, new InternetAddress(vo.getReceiveMail()));
			//이메일 발신자
			msg.addFrom(new InternetAddress[] {
					new InternetAddress(vo.getSenderMail(), vo.getSenderName())
			});
			//이메일 제목
			msg.setSubject(vo.getSubject(),"utf-8");
			//이메일 본문
			msg.setText(vo.getMessage(),"utf-8");
			mailSender.send(msg); //전송
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
