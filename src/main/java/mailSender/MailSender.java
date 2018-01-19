package mailSender;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailSender
{
  public static final String DEFAULT_CONTENT_TYPE = "text/plain";
  public static final String DEFAULT_ENCODING = "UTF-8";
  public static final int DEFAULT_PORT = 25;
  private String host = "";
  private String from = "";
  private String user = "";
  private String password = "";
  private String subject = "";
  private String text = "";
  private String contentType = "text/plain";
  private String charset = DEFAULT_ENCODING;
  private int port = 25;
  private boolean auth = true;
  private boolean needReceipt = false;
  private Date sentDate = null;

  private List<String> to = new ArrayList();
  private List<String> cc = new ArrayList();
  private List<String> bcc = new ArrayList();
  private List<String> replyTo = new ArrayList();
  private List<String> fileAcc = new ArrayList();
  private List<MimeBodyPart> byteAcc = new ArrayList();

  public int getPort() {
    return this.port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public boolean isAuth() {
    return this.auth;
  }

  public void setAuth(boolean auth) {
    this.auth = auth;
  }

  public String getCharset() {
    return this.charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public String getContentType() {
    return this.contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public boolean isNeedReceipt() {
    return this.needReceipt;
  }

  public void setNeedReceipt(boolean needReceipt) {
    this.needReceipt = needReceipt;
  }

  public String getFrom() {
    return this.from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getHost() {
    return this.host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getSentDate() {
    return this.sentDate;
  }

  public void setSentDate(Date sentDate) {
    this.sentDate = sentDate;
  }

  public String getUser() {
    return this.user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public List<String> getFileAcc() {
    return this.fileAcc;
  }

  public List<MimeBodyPart> getByteAcc() {
    return this.byteAcc;
  }

  public void setFileAcc(List<String> accessory) {
    this.fileAcc = accessory;
  }

  public void setByteAcc(List<MimeBodyPart> accessory) {
    this.byteAcc = accessory;
  }

  public List<String> getReplyTo() {
    return this.replyTo;
  }

  public List<String> getTo() {
    return this.to;
  }

  public void setTo(List<String> to) {
    this.to = to;
  }

  public List<String> getCc() {
    return this.cc;
  }

  public void setCc(List<String> cc) {
    this.cc = cc;
  }

  public List<String> getBcc() {
    return this.bcc;
  }

  public void setBcc(List<String> bcc) {
    this.bcc = bcc;
  }

  public void addFileAcc(String accessory) {
    this.fileAcc.add(accessory);
  }

//  public void addByteAcc(byte[] accessory, String type, String fileName)
//    throws Exception
//  {
//    ByteArrayDataSource ds = new ByteArrayDataSource(accessory, type, 
//      fileName);
//
//    fileName = MimeUtility.encodeText(fileName, this.charset, "B");
//
//    MimeBodyPart mimeFile = new MimeBodyPart();
//    mimeFile.setDataHandler(new DataHandler(ds));
//
//    mimeFile.setFileName(fileName);
//
//    this.byteAcc.add(mimeFile);
//  }

  public void addReplyTo(String address) {
    this.replyTo.add(address);
  }

  public void addTo(String address) {
    this.to.add(address);
  }

  public void addCc(String address) {
    this.cc.add(address);
  }

  public void addBcc(String address) {
    this.bcc.add(address);
  }

  public void send() throws Exception
  {
    Transport transport = null;
    try
    {
      Properties props = new Properties();
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.auth", Boolean.toString(this.auth));

      Session session = Session.getDefaultInstance(props, null);
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(this.from));

      for (String i : this.to)
        msg.addRecipient(Message.RecipientType.TO, 
          new InternetAddress(i));
      for (String i : this.cc)
        msg.addRecipient(Message.RecipientType.CC, 
          new InternetAddress(i));
      for (String i : this.bcc) {
        msg.addRecipient(Message.RecipientType.BCC, 
          new InternetAddress(i));
      }
      if (this.replyTo.size() > 0) {
        InternetAddress[] replyAddress = new InternetAddress[this.replyTo
          .size()];

        for (int i = 0; i < replyAddress.length; i++) {
          replyAddress[i] = 
            new InternetAddress((String)this.replyTo.get(i));
        }
        msg.setReplyTo(replyAddress);
      }

      if (this.needReceipt) {
        msg.addHeader("Disposition-Notification-To", this.from);
      }
      if (this.sentDate != null)
        msg.setSentDate(this.sentDate);
      else {
        msg.setSentDate(new Date());
      }
      msg.setSubject(this.subject, this.charset);

      MimeMultipart mm = new MimeMultipart();
      MimeBodyPart mbText = new MimeBodyPart();
      mbText.setContent(this.text, this.contentType + ";charset=" + this.charset);
      mm.addBodyPart(mbText);

      for (String filePath : this.fileAcc) {
        String fileName = new File(filePath).getName();
        fileName = MimeUtility.encodeText(fileName, this.charset, "B");

        MimeBodyPart mbFile = new MimeBodyPart();
        DataSource datasource = new FileDataSource(filePath);

        mbFile.setDataHandler(new DataHandler(datasource));
        mbFile.setFileName(fileName);
        mm.addBodyPart(mbFile);
      }

      for (MimeBodyPart part : this.byteAcc) {
        mm.addBodyPart(part);
      }
      msg.setContent(mm);
      msg.saveChanges();

      transport = session.getTransport();
      transport.connect(this.host, this.port, this.user, this.password);
      transport.sendMessage(msg, msg.getAllRecipients());
    } finally {
      if (transport != null)
        try {
          transport.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
  }

  /**
   * 修改密码直接执行
   * @param arg
   */
  public static void main(String[] arg) {
    MailSender ms = new MailSender();
    ms.setHost("smtp.ym.163.com");
    ms.setFrom("XXX@trig**e.com");
    ms.setUser("XXX@trig**e.com");
    ms.setPassword("");
    List to = new ArrayList();
    to.add("xxx@qq.com");
    ms.setTo(to);
    ms.setText("test1");//内容
    ms.setSubject("test2");//标题
    try
    {
      ms.send();
      System.out.println("发送成功！");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

