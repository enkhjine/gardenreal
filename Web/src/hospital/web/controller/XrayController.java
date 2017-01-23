package hospital.web.controller;

import hospital.annotation.Label;
import hospital.businessentity.CustomerColonoscopic;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicMenuLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.CustomerAttachment;
import hospital.entity.CustomerPastHistory;
import hospital.entity.Employee;
import hospital.entity.EndoTemplate;
import hospital.entity.EndoscopyDetail;
import hospital.entity.ExaminationRequestCompleted;
import hospital.entity.Ultrasound;
import hospital.entity.Xray;
import hospital.entity.XrayDtl;
import hospital.entity.XrayEmployeeMap;
import hospital.entity.XrayRequest;
import hospital.entity.XrayType;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.castor.core.util.Base64Encoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import hospital.entity.XrayPrice;
import hospital.web.controller.ApplicationController;
import hospital.web.controller.UserSessionController;
import logic.data.Tools;
import org.primefaces.model.CroppedImage;

@SessionScoped
@ManagedBean(name = "xrayController")
public class XrayController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;

	@EJB(beanName = "LogicInspection")
	IInspectionLogicLocal logicInspection;

	@EJB(beanName = "LogicTreatment")
	ILogicTreatmentLocal logicTreatment;

	@EJB(beanName = "LogicXray")
	ILogicXrayLocal logicXray;

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@EJB(beanName = "LogicMenu")
	ILogicMenuLocal menuLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;

	@ManagedProperty(value = "#{inspectionController}")
	private InspectionController inspectionController;

	// Region - Controllers

	// Lists
	private List<Xray> xrays;
	private List<XrayType> xrayTypes;
	private List<XrayRequest> xrayRequests;
	private List<XrayPrice> xrayPrices;
	private List<String> xrayEmployee;
	private List<Employee> employees;
	private List<CustomerAttachment> xrayAttachments;
	private List<CustomerAttachment> xrayColonoAttachmens;
	private List<EndoTemplate> endoTemplates;

	// Cursors
	private Xray currentXray;
	private XrayType currentXrayType;
	private String filterKey;
	private EndoTemplate currentEndoTemplate;

	// Filter
	private Date filterDate1;
	private Date filterDate2;
	private boolean isDone;
	/* filter by Customer detail */

	private String searchKey;
	/* filter by Employee */
	private String searchKey1;
	private String imageType;

	// Property
	private XrayRequest currentXrayRequest;
	private StreamedContent imageRentgen;
	private CustomerPastHistory currentPastHistory;
	private List<ExaminationRequestCompleted> requestList;
	private EndoscopyDetail esDtl;
	private Ultrasound ultrasound;
	private CustomerAttachment attachment;
	private String attachmentName;

	private CustomerColonoscopic customerColonoscopic;
	private DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private List<JSONObject> parentList;

	private List<EndoscopyDetail> endoCopyDetialList;
	private List<XrayDtl> xrayDtlList;
	private XrayRequest xrayRequestCustomer;
	private XrayDtl xrayDtlMood;

	private JSONObject colonoJson;
	private XrayDtl xrayDtl;
	private JSONObject jsonColono;
	private String displayStatus = "block";
	private List<String> models;
	private String modelValue;
	boolean renderable = false;
	private String imageName;
	private String imgSrc;
	int sliderCursor;

	// EndRegion

	// Region - Methods
	public XrayController() {
		super();
	}

	public void setCurrentXrayRequestImage(XrayRequest request) {
		RequestContext context = RequestContext.getCurrentInstance();
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		request.setImageUrl(applicationController.getImagePath() + format.format(request.getRequestDate()) + "/"
				+ request.getImage());
		File imageFile = new File(request.getImageUrl());
		System.out.println("FILE URL ========> " + request.getImageUrl());
		try {
			FileInputStream in = new FileInputStream(imageFile);
			setImageRentgen(new DefaultStreamedContent(in, "image/png"));
			setImgSrc(request.getImageUrl());
			System.out.println("IMGNAME" + imgSrc);
		} catch (FileNotFoundException ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		context.update("form:pppp");
		setCurrentXrayRequest(request);
	}

	public void saveEndoTemplate() {
		try {
			if (currentEndoTemplate.getName() != null || currentEndoTemplate.getValue() != null) {
				logicXray.saveTemplate(currentEndoTemplate, userSessionController.getLoggedInfo());
				endoTemplates = logicXray.getTemplates();
				userSessionController.showSuccessMessage("Ð�Ð¼Ð¶Ð¸Ð»Ñ‚Ñ‚Ð°Ð¹");
			}
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('newModel').hide();");
			context.update("form:cm");

		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void printColono() {
		try {
			xrayRequestCustomer = logicXray.getXrayRequestByCustomer(getCurrentXrayRequest().getCustomerPkId());
			jsonColono = toJson(getCustomerColonoscopic());
			System.out.println(jsonColono.get("p"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void modelSelect(EndoTemplate et) {
		setModelValue(et.getValue());
		RequestContext context = RequestContext.getCurrentInstance();
		setCurrentEndoTemplate(et);
		currentEndoTemplate.setStatus(Tool.MODIFIED);
		context.update("form:modelValue");
		context.update("form:templateSection");

	}

	public void chooseModel() {
		RequestContext context = RequestContext.getCurrentInstance();
		esDtl.setAllData(getModelValue());
		context.execute("PF('chooseModel').hide();");
		userSessionController.showSuccessMessage("Ð—Ð°Ð³Ð²Ð°Ñ€ Ñ�Ð¾Ð½Ð³Ð¾Ð³Ð´Ð»Ð¾Ð¾");
	}

	public void provideToggleListener() {
		Map<String, String> mapPara = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String provide = mapPara.get("provide");
		customerColonoscopic
				.setProvide("<span style='font-size: 13px; font-weight: 700; font-family: Times New Roman;'>" + provide
						+ "</span>");

	}

	public void endoScopicToggleListener() {
		Map<String, String> mapP = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String endoString = mapP.get("endo");
		customerColonoscopic
				.setEndo("<div></div><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman;'>"
						+ endoString + "</span>");
	}

	public void emrShowResult(XrayRequest xrayRequest) {
		if (Tool.ENDO.equals(xrayRequest.getWindowType())) {
			setCurrentXrayRequest(xrayRequest);
			prepareData();
			RequestContext.getCurrentInstance().update("form:endoScopeDtl");
		}
		if (Tool.ULTRA.equals(xrayRequest.getWindowType())) {
			setCurrentXrayRequest(xrayRequest);

		}
	}

	public void prepareData() {
		try {
			esDtl = logicXray.getEndoDtl(currentXrayRequest.getPkId());
			RequestContext context = RequestContext.getCurrentInstance();
			if (esDtl != null) {
				xrayAttachments = logicInspection.getCustomerAttachment(currentXrayRequest.getPkId());
			}
			getEndoTemplates();
			endoTemplates = logicXray.getTemplates();

			if (esDtl == null) {
				esDtl = new EndoscopyDetail();
				esDtl.setStatus(Tool.ADDED);
				xrayAttachments = new ArrayList<CustomerAttachment>();
			} else
				esDtl.setStatus(Tool.MODIFIED);

			if (esDtl != null)
				context.execute("loadDataPls( ' " + esDtl.getAllValue() + " ');");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void printPrepare() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:endoPrint");
		context.update("form:esPrint");
		context.update("form:ultra_data");
		setRenderable(true);

	}

	public void deleteFile(List<CustomerAttachment> list, CustomerAttachment attachment) {
		System.out.println("SIZE HERE ========>" + list.size());
		File file = new File(attachment.getFileName());
		list.remove(attachment);
		try{
			saveEndoscope();
			RequestContext.getCurrentInstance().update("form:xrayInspection:galleria");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		if (file.isFile())
			System.out.println("TRUE PLEASE");
		file.delete();
	}

	public void setFileName() {
		getAttachment();
		attachment.setDescription(attachmentName);
		setAttachmentName("");
		RequestContext.getCurrentInstance().update("form:xrayInspection:galleria");
		RequestContext.getCurrentInstance().update("form:attachmentName");

	}

	public String expandModule() {
		getInspectionController().setSelectedCustomer(currentXrayRequest.getCustomer());
		String ret = "";
		System.out.println(getCurrentXrayRequest().getXrayType());

		try {
			if (getCurrentXrayRequest().getXrayType().equals(Tool.ENDO)) {
				ret = "xray_module";
				prepareData();
			} else if (getCurrentXrayRequest().getXrayType().equals("DEFAULT")) {
				ret = "xray_moduleColonoScopic";
				try {
					xrayDtlMood = logicXray.getListXrayDtl(getCurrentXrayRequest().getPkId());
					xrayDtl = logicXray.getListXrayDtl(getCurrentXrayRequest().getPkId());
					if (xrayDtl == null) {
						xrayDtl = new XrayDtl();
						xrayDtl.setStatus(Tool.ADDED);
						customerColonoscopic = new CustomerColonoscopic();
					} else
						xrayDtl.setStatus(Tool.MODIFIED);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (getCurrentXrayRequest().getXrayType().equals(Tool.ULTRA)) {
				ret = "xray_ultra";
			} else if (getCurrentXrayRequest().getXrayType().equals(Tool.INSPECTIONTYPE_XRAY))
				ret = "xray_xrayds";
			currentPastHistory = logicInspection.getCustomerPast(getCurrentXrayRequest().getCustomer().getPkId());
		} catch (

		Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}
		return ret;

	}

	public void saveEndoscope() {
		try {
			getEsDtl().setXrayRequestPkId(getCurrentXrayRequest().getPkId());
			List<CustomerAttachment> attachment = new ArrayList<CustomerAttachment>();
			attachment.addAll(getXrayAttachments());
			logicXray.saveEndoscope(userSessionController.getLoggedInfo(), getEsDtl(), attachment);
			userSessionController.showMessage(99);
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void prepareUltra() {
		try {
			ultrasound = logicXray.getUltrasound(currentXrayRequest.getPkId());
			if (ultrasound == null) {
				ultrasound = new Ultrasound();
				ultrasound.setStatus(Tool.ADDED);
			} else
				ultrasound.setStatus(Tool.MODIFIED);
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void saveUltrasound() {
		try {
			ultrasound.setXrayRequestPkId(currentXrayRequest.getPkId());
			logicXray.saveUltrasound(userSessionController.getLoggedInfo(), ultrasound);
			userSessionController.showMessage(99);

		}

		catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}

	public void upload1(FileUploadEvent event) {
		try {

			CustomerAttachment att = new CustomerAttachment();
			att.setPkId(Tools.newPkId());
			att.setAttachmentType(imageType);
			att.setFileName(buildAttachmentFileName(att.getPkId().toString(), event.getFile().getFileName()));
			att.setStatus(Tool.ADDED);
			OutputStream out;
			InputStream in = event.getFile().getInputstream();

			out = new FileOutputStream(new File(att.getFileName()));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();
			if ("endo".equals(imageType)) {
				getXrayAttachments().add(att);
				RequestContext.getCurrentInstance().update("form:xrayInspection:galleria");
				// RequestContext.getCurrentInstance().execute("makeZoomable()");
			} else if ("colonoScopic".equals(imageType)) {
				getXrayColonoAttachmens().add(att);
				RequestContext.getCurrentInstance().update("form:xrayInspectionColono:galleria");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	private String buildAttachmentFileName(String pkId, String fileName) {
		String path = applicationController.getInspectionImagePath() + imageType + "/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		return path + pkId + "." + FilenameUtils.getExtension(fileName);
	}

	public void showSelectedImage(XrayRequest request, String fileName) {
		RequestContext context = RequestContext.getCurrentInstance();
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		setSliderCursor(0);
		request.setImageUrl(applicationController.getImagePath() + format.format(request.getRequestDate()) + "/"
				+ request.getImgList().get(sliderCursor));
		try {
			Path path = Paths.get(request.getImageUrl());
			byte[] buf = Files.readAllBytes(path);
			getImgSrc();
			imgSrc = "data:image/png;base64," + new String(Base64Encoder.encode(buf));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		File imageFile = new File(request.getImageUrl());
		try {
			FileInputStream in = new FileInputStream(imageFile);
			setImageRentgen(new DefaultStreamedContent(in, "file", fileName));
		} catch (FileNotFoundException ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

		context.update("form:pppp");
		setCurrentXrayRequest(request);

	}
	
	public void showSelectedImage(XrayRequest request) {
		RequestContext context = RequestContext.getCurrentInstance();
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		setSliderCursor(0);
		request.setImageUrl(applicationController.getImagePath() + format.format(request.getRequestDate()) + "/"
				+ request.getImgList().get(sliderCursor));
		try {
			Path path = Paths.get(request.getImageUrl());
			byte[] buf = Files.readAllBytes(path);
			getImgSrc();
			imgSrc = "data:image/png;base64," + new String(Base64Encoder.encode(buf));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		context.update("form:pppp");
		setCurrentXrayRequest(request);

	}
	
	

	public void slide(boolean a) {

		if (currentXrayRequest.getImgList().size() > sliderCursor) {

			if (a)
				sliderCursor++;
			else
				sliderCursor--;
			if (sliderCursor == currentXrayRequest.getImgList().size())
				sliderCursor = 0;
			if (sliderCursor == -1)
				sliderCursor = currentXrayRequest.getImgList().size() - 1;
			DateFormat format = new SimpleDateFormat("yyyy-MM");
			currentXrayRequest.setImageUrl(
					applicationController.getImagePath() + format.format(currentXrayRequest.getRequestDate()) + "/"
							+ currentXrayRequest.getImgList().get(sliderCursor));
			try {
				Path path = Paths.get(currentXrayRequest.getImageUrl());
				byte[] buf = Files.readAllBytes(path);
				getImgSrc();
				imgSrc = "data:image/png;base64," + new String(Base64Encoder.encode(buf));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			sliderCursor = 0;
			DateFormat format = new SimpleDateFormat("yyyy-MM");
			currentXrayRequest.setImageUrl(
					applicationController.getImagePath() + format.format(currentXrayRequest.getRequestDate()) + "/"
							+ currentXrayRequest.getImgList().get(sliderCursor));
			try {
				Path path = Paths.get(currentXrayRequest.getImageUrl());
				byte[] buf = Files.readAllBytes(path);
				getImgSrc();
				imgSrc = "data:image/png;base64," + new String(Base64Encoder.encode(buf));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:pppp");
		context.execute("makeZoomable();");

	}

	public StreamedContent getDynamicImage(int imageId1) {
		String imageId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("image_id");
		if (imageId1 == -1)
			return getStreamedImage(getXrayAttachments().get(Integer.parseInt(imageId)).getFileName());
		return getStreamedImage(getXrayAttachments().get(imageId1).getFileName());

	}

	public String getDynamicImageSrc(int imageId1) {
		String path = "";
		String ret = "";
		String imageId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("image_id");
		if (imageId1 == -1)
			path = getXrayAttachments().get(Integer.parseInt(imageId)).getFileName();
		path = getXrayAttachments().get(imageId1).getFileName();

		try {
			Path path1 = Paths.get(path);
			byte[] buf = Files.readAllBytes(path1);
			getImgSrc();
			ret = "data:image/png;base64," + new String(Base64Encoder.encode(buf));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;

	}
	
	public String pathToBase64(String path)
	{
		String ret = "";
		
		try {
			Path path1 = Paths.get(path);
			byte[] buf = Files.readAllBytes(path1);
			getImgSrc();
			ret = "data:image/png;base64," + new String(Base64Encoder.encode(buf));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
		
	}

	public StreamedContent loadRequestImages(int id) {
		String imageId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("image_id");
		if (id == -1)
			return getStreamedImagef(getCurrentXrayRequest().getImgList().get(Integer.parseInt(imageId)));

		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!" + getCurrentXrayRequest().getImgList().size());
		return getStreamedImagef(getCurrentXrayRequest().getImgList().get(id));
	}

	public StreamedContent getColonoDynamicImage(int imageId1) {
		String imageId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("image_id");
		if (imageId1 == -1)
			return getStreamedImage(getXrayColonoAttachmens().get(Integer.parseInt(imageId)).getFileName());
		return getStreamedImage(getXrayColonoAttachmens().get(imageId1).getFileName());
	}

	public String columnDivide(int index1) {
		if ((index1 + 1) % 3 == 0)
			return "<div style=\"width: 100%; float:left; height:1px\"></div>";
		return "";
	}

	public StreamedContent getStreamedImage(String fileName) {
		StreamedContent content = null;
		System.out.println("FILE NAME ======> " + fileName);
		try {
			File imageFile = new File(fileName);
			FileInputStream in = new FileInputStream(imageFile);
			content = new CustomDefaultStreamedContent(in, "image/" + getExtension(fileName));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return content;
	}

	public StreamedContent getStreamedImagef(String fileName) {
		StreamedContent content = null;
		System.out.println("FILE NAME ======> " + fileName);
		try {
			File imageFile = new File(getPath(currentXrayRequest) + fileName);
			FileInputStream in = new FileInputStream(imageFile);
			content = new CustomDefaultStreamedContent(in, "image/" + getExtension(fileName));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return content;
	}

	private String getExtension(String fileName) {
		for (int i = 0; i < fileName.length(); i++) {
			if (".".equals(fileName.substring(i, i + 1))
					&& !fileName.substring(i + 1, fileName.length()).contains(".")) {
				return fileName.substring(i + 1, fileName.length());
			}
		}
		return "";
	}

	public void deleteImage(XrayRequest dr) {
		try {
			dr.setImage(null);
			logicInspection.saveXrayRequest(dr);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}

	public String saveXrayDs() {
		String ret = "";
		try {
			logicInspection.saveXrayRequest(currentXrayRequest);
			ret = "xray_request";
			userSessionController.showMessage(99);
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}
		return ret;

	}

	public void fillCustomerExamination(BigDecimal customerPkId) {
		try {
			getRequestList().clear();
			requestList = menuLogic.getCompletedRequests(customerPkId);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('exaRequestList').show();");
			context.update("form:exaRequestList");
			context.update("form:exaList");

		} catch (Exception ex) {

		}
	}

	public void deleteSelectedImage(XrayRequest dr, String fileName) {
		try {
			getCurrentXrayRequest();
			currentXrayRequest = dr;
			currentXrayRequest.setImage(currentXrayRequest.getImage().replaceAll(fileName + "\\?", ""));
			logicInspection.saveXrayRequest(currentXrayRequest);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	private String getFileName(String name) {
		return currentXrayRequest.getCustomer().getFirstName() + "_" + name;
	}

	private String getPath(XrayRequest request) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = request.getRequestDate();
		String path = applicationController.getImagePath() + dateFormat.format(date) + "/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return path;
	}

	public void upload(FileUploadEvent event) {
		try {
			if (copyFile(getPath(currentXrayRequest) + Tool.CToL(getFileName(event.getFile().getFileName())),
					event.getFile().getInputstream())) {
				if (currentXrayRequest.getImage() != null)
					currentXrayRequest.setImage(currentXrayRequest.getImage()
							+ Tool.CToL(getFileName(event.getFile().getFileName() + "?")));
				else
					currentXrayRequest.setImage(Tool.CToL(getFileName(event.getFile().getFileName())) + "?");
				logicInspection.saveXrayRequest(currentXrayRequest);
				userSessionController.showMessage(99);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	private boolean copyFile(String fileName, InputStream in) {
		try {
			// write the inputStream to a FileOutputStream
			OutputStream out;
			out = new FileOutputStream(new File(fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			System.out.println("Created file ==========> " + fileName);
			in.close();
			out.flush();
			out.close();
			return true;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return false;
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
			return false;
		}
	}

	public void base64toFile() {
//		try {
//			List<XrayRequest> requestList;
//			requestList = logicXray.getBase64Images();
//			System.out.println("SIZE =======> " + requestList.size());
//			BASE64Decoder decoder = new BASE64Decoder();
//			for (XrayRequest xrayRequest : requestList) {
//				ByteArrayInputStream bais = new ByteArrayInputStream(
//						decoder.decodeBuffer(xrayRequest.getImage().split("base64,", 2)[1]));
//				String imageName = "";
//				if (xrayRequest.getCustomer().getRegNumber() == null)
//					imageName = "null" + "_" + xrayRequest.getPkId() + ".jpeg";
//				else
//					imageName = Tool.CToL(xrayRequest.getCustomer().getRegNumber()) + "_" + xrayRequest.getPkId()
//							+ ".jpeg";
//
//				copyFile(getPath(xrayRequest) + imageName, bais);
//				xrayRequest.setImage(imageName);
//			}
//			logicInspection.saveXrayRequests(requestList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public void editDesc(XrayRequest request) {
		request.setEdit(false);
		request.setDescription(request.getDescriptionTemp());
		try {
			logicInspection.saveXrayRequest(request);
			userSessionController.showMessage(99);
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage(
					"Ãƒâ€œÃ‚Â¨Ãƒï¿½Ã‚Â³Ãƒâ€œÃ‚Â©Ãƒï¿½Ã‚Â³Ãƒï¿½Ã‚Â´Ãƒâ€œÃ‚Â©Ãƒï¿½Ã‚Â» Ãƒâ€˜Ã¢â‚¬Â¦Ãƒï¿½Ã‚Â°Ãƒï¿½Ã‚Â´Ãƒï¿½Ã‚Â³Ãƒï¿½Ã‚Â°Ãƒï¿½Ã‚Â»Ãƒï¿½Ã‚Â°Ãƒâ€˜Ã¢â‚¬Â¦Ãƒï¿½Ã‚Â°Ãƒï¿½Ã‚Â´ Ãƒï¿½Ã‚Â°Ãƒï¿½Ã‚Â»Ãƒï¿½Ã‚Â´Ãƒï¿½Ã‚Â°Ãƒï¿½Ã‚Â° Ãƒï¿½Ã‚Â³Ãƒï¿½Ã‚Â°Ãƒâ€˜Ã¢â€šÂ¬Ãƒï¿½Ã‚Â»Ãƒï¿½Ã‚Â°Ãƒï¿½Ã‚Â°.");
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:xrayRequests");
	}

	public void setEditable(XrayRequest request) {
		request.setEdit(true);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:xrayRequests");
	}

	public void cancelEditDescription(XrayRequest request) {
		request.setEdit(false);
		request.setDescriptionTemp(request.getDescription());
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:xrayRequests");
	}

	public void test(XrayRequest request) {
		System.out.println("REquest here =========> " + request.getDescription());
	}

	// EndRegion

	// Region - getter setter

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public String getSearchKey1() {
		if (searchKey1 == null) {
			searchKey1 = new String();
			searchKey1 = "";
		}
		return searchKey1;
	}

	public void setSearchKey1(String searchKey1) {
		this.searchKey1 = searchKey1;
	}

	public String getSearchKey() {
		if (searchKey == null) {
			searchKey = new String();
			searchKey = "";
		}
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public void setDone(boolean isDone) {
		refreshXrayRequests();
		this.isDone = isDone;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public List<Xray> getXrays() {
		if (xrays == null)
			xrays = new ArrayList<Xray>();
		return xrays;
	}

	public void setXrays(List<Xray> xrays) {
		this.xrays = xrays;
	}

	public List<XrayType> getXrayTypes() {
		if (xrayTypes == null)
			xrayTypes = new ArrayList<XrayType>();
		return xrayTypes;
	}

	public void setXrayTypes(List<XrayType> xrayTypes) {
		this.xrayTypes = xrayTypes;
	}

	public Xray getCurrentXray() {
		if (currentXray == null)
			newXray();
		return currentXray;
	}

	public void setCurrentXray(Xray currentXray) {
		this.currentXray = currentXray;
	}

	public XrayType getCurrentXrayType() {
		if (currentXrayType == null)
			currentXrayType = new XrayType();
		return currentXrayType;
	}

	public void setCurrentXrayType(XrayType currentXrayType) {
		this.currentXrayType = currentXrayType;
	}

	public String saveXray() {
		String ret = "";

		if (currentXray.getName() == null || currentXray.getName() == "") {
			userSessionController.showMessage(55);
			return ret;
		}
		if (currentXray.getStatus().equals(Tool.MODIFIED)) {
			if (currentXray.getPriceInUsageDate() == null) {
				userSessionController.showMessage(82);
				return ret;
			} else if (currentXray.getPriceInUsageDate().before(new Date())) {
				userSessionController.showMessage(83);
				return ret;
			}
		}

		List<XrayEmployeeMap> maps = new ArrayList<>();

		if (xrayEmployee != null && xrayEmployee.size() > 0) {
			for (String string : xrayEmployee) {
				XrayEmployeeMap map = new XrayEmployeeMap();
				map.setEmployeePkId(new BigDecimal(string));
				maps.add(map);
			}
		}

		try {
			logicXray.saveXray(userSessionController.getLoggedInfo(), currentXray, maps);
			userSessionController.showMessage(99);
			currentXray = null;
			xrays = logicXray.getXrays();
			ret = "xray_list";
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}

		return ret;
	}

	public void saveColonoScopic() {
		System.out.println("reguest" + getCurrentXrayRequest().getPkId());
		colonoJson = toJson(getCustomerColonoscopic());
		getCurrentXrayRequest();
		getXrayDtl();
		getXrayDtlList();
		Date date = new Date();
		xrayDtl.setXrayInspectionPain(((JSONArray) colonoJson.get("p")).toString());
		xrayDtl.setCreateDate(date);
		xrayDtl.setRequestSaveMood(1);
		xrayDtlList.add(xrayDtl);
		try {
			logicXray.saveColonoScopic(getCurrentXrayRequest().getPkId(), xrayDtlList, getXrayAttachments());
			getUserSessionController().showMessage(99);
			getXrayDtlList().clear();
			setXrayDtl(null);

		} catch (Exception e) {
			userSessionController.showErrorMessage(" xrayColono" + e.getMessage());
			e.printStackTrace();
		}
	}

	public String saveColonoScopicComplete() {
		String ret = "xray_moduleColonoScopic";
		colonoJson = toJson(getCustomerColonoscopic());
		getXrayDtl();
		getXrayDtlList();
		Date date = new Date();
		xrayDtl.setXrayInspectionPain(((JSONArray) colonoJson.get("p")).toString());
		xrayDtl.setCreateDate(date);
		xrayDtl.setRequestSaveMood(2);
		xrayDtlList.add(xrayDtl);
		try {
			logicXray.saveColonoScopic(getCurrentXrayRequest().getPkId(), xrayDtlList, getXrayAttachments());
			ret = "xray_request";
			getUserSessionController().showMessage(100);

		} catch (Exception e) {
			userSessionController.showErrorMessage(" xrayColono" + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public String saveXrayType() {
		String ret = "";
		if (currentXrayType.getName() == null || currentXrayType.getName() == "") {
			userSessionController.showMessage(54);
			return ret;
		} else {
			try {

				logicXray.saveXrayType(userSessionController.getLoggedInfo(), currentXrayType);

				currentXray.setXrayTypePkId(currentXrayType.getPkId());

				ret = "xray_list";
				currentXrayType = null;
				userSessionController.showMessage(99);
				loadData();
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:xrayType form:dtname");
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());

			}
		}

		return ret;
	}

	public void newXray() {
		currentXray = new Xray();
		try {
			employees = infoLogic.getEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		currentXray.setStatus(Tool.ADDED);
	}

	public void selectedEmployees() {
		System.out.println("selected employee size :  " + xrayEmployee.size());
		for (String string : xrayEmployee) {
			System.out.println("Employee PkId:" + string.toString());
		}
	}

	public void modifiedXray(Xray xray) {
		currentXray = (Xray) Tool.deepClone(xray);
		if (xrayEmployee == null)
			xrayEmployee = new ArrayList<>();
		else
			xrayEmployee.clear();
		try {
			for (XrayEmployeeMap map : logicXray.getXrayEmployeeMap(xray.getPkId())) {
				xrayEmployee.add(map.getEmployeePkId().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		currentXray.setStatus(Tool.MODIFIED);
	}

	public void deleteXray(Xray xray) {
		currentXray = xray;
		currentXray.setStatus(Tool.DELETE);
	}

	//
	public String deleteXray() {
		try {

			if (infoLogic.isRelated(currentXray.getPkId(), 6)) {
				logicXray.saveXray(userSessionController.getLoggedInfo(), currentXray,
						new ArrayList<XrayEmployeeMap>());
				userSessionController.showMessage(98);
			} else {
				currentXray.setStatus(Tool.UNCHANGED);
				userSessionController.showErrorMessage("Ð£Ñ�Ñ‚Ð³Ð°Ñ… Ð±Ð¾Ð»Ð¾Ð¼Ð¶Ð³Ò¯Ð¹");

			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
		return "xray_list";
	}

	public void newXrayType() {
		currentXrayType = new XrayType();
		currentXrayType.setStatus(Tool.ADDED);
	}

	public String modifiedXrayType(XrayType xrayType) {
		currentXrayType = xrayType;
		currentXrayType.setStatus(Tool.MODIFIED);
		return "xray_list";
	}

	public void deleteXrayType(XrayType xrayType) {

		currentXrayType = xrayType;
		currentXrayType.setStatus(Tool.DELETE);
	}

	//
	public String deleteXrayType() {
		try {
			logicXray.saveXrayType(userSessionController.getLoggedInfo(), currentXrayType);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
		return "xray_list";
	}

	public void filterXrayByXrayType(String son) {
		try {
			xrays = logicXray.getXrays();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

		ArrayList<Xray> filtered = new ArrayList<Xray>();
		if ("all".equals(son) || son == null || "".equals(son)) {

		}

		else {
			for (Xray d : xrays) {
				if (d.getXrayTypeName().equals(son))
					filtered.add(d);
			}
			xrays = filtered;
		}
	}

	public String getFilterKey() {
		return filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	public void loadData() {
		getXrays();
		getXrayTypes();

		try {
			setXrays(logicXray.getXrays());
			setXrayTypes(logicXray.getXrayTypes(userSessionController.getLoggedInfo()));

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:dtname");

	}

	public void refreshXrayRequests() {
		getXrayRequests();
		try {
			xrayRequests = logicXray.getXrayRequests(filterDate1, filterDate2, getSearchKey(), isDone, getSearchKey1(),
					userSessionController.getLoggedInfo().getEmployeePkId());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public List<XrayRequest> getXrayRequests() {
		if (xrayRequests == null) {
			xrayRequests = new ArrayList<XrayRequest>();
		}

		return xrayRequests;
	}

	public void setXrayRequests(List<XrayRequest> xrayRequests) {
		this.xrayRequests = xrayRequests;
	}

	public Date getFilterDate1() {
		if (filterDate1 == null)
			filterDate1 = new Date();
		filterDate1.setHours(0);
		filterDate1.setMinutes(0);
		return filterDate1;
	}

	public void setFilterDate1(Date filterDate1) {
		this.filterDate1 = filterDate1;
	}

	public Date getFilterDate2() {
		if (filterDate2 == null)
			filterDate2 = new Date();
		filterDate2.setHours(23);
		filterDate2.setMinutes(59);
		return filterDate2;
	}

	public void setFilterDate2(Date filterDate2) {
		this.filterDate2 = filterDate2;
	}

	public XrayRequest getCurrentXrayRequest() {
		if (currentXrayRequest == null) {
			currentXrayRequest = new XrayRequest();
		}
		return currentXrayRequest;
	}

	public void setCurrentXrayRequest(XrayRequest currentXrayRequest) {
		this.currentXrayRequest = currentXrayRequest;
	}

	public StreamedContent getImageRentgen() {
		return imageRentgen;
	}

	public void setImageRentgen(StreamedContent imageRentgen) {
		this.imageRentgen = imageRentgen;
	}

	public List<XrayPrice> getXrayPrices() {
		if (xrayPrices == null)
			xrayPrices = new ArrayList<XrayPrice>();
		return xrayPrices;
	}

	public void setXrayPrices(List<XrayPrice> xrayPrices) {
		this.xrayPrices = xrayPrices;
	}

	public void getXrayPricesByPkId(BigDecimal xrayPkId) {
		try {
			setXrayPrices(logicXray.getXrayPrices(xrayPkId));
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:xrayPrices");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
	}

	public List<String> getXrayEmployee() {
		return xrayEmployee;
	}

	public void setXrayEmployee(List<String> xrayEmployee) {
		this.xrayEmployee = xrayEmployee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<ExaminationRequestCompleted> getRequestList() {
		if (requestList == null)
			requestList = new ArrayList<ExaminationRequestCompleted>();
		return requestList;
	}

	public void setRequestList(List<ExaminationRequestCompleted> requestList) {
		this.requestList = requestList;
	}

	public List<CustomerAttachment> getXrayAttachments() {
		if (xrayAttachments == null)
			xrayAttachments = new ArrayList<CustomerAttachment>();
		return xrayAttachments;
	}

	public void setXrayAttachments(List<CustomerAttachment> xrayAttachments) {
		this.xrayAttachments = xrayAttachments;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public CustomerPastHistory getCurrentPastHistory() {
		if (currentPastHistory == null)
			currentPastHistory = new CustomerPastHistory();
		return currentPastHistory;
	}

	public void setCurrentPastHistory(CustomerPastHistory currentPastHistory) {
		this.currentPastHistory = currentPastHistory;
	}

	public EndoscopyDetail getEsDtl() {
		if (esDtl == null)
			esDtl = new EndoscopyDetail();
		return esDtl;
	}

	public void setEsDtl(EndoscopyDetail esDtl) {
		this.esDtl = esDtl;
	}

	public CustomerAttachment getAttachment() {
		if (attachment == null)
			attachment = new CustomerAttachment();
		return attachment;
	}

	public void setAttachment(CustomerAttachment attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public CustomerColonoscopic getCustomerColonoscopic() {
		if (customerColonoscopic == null) {
			customerColonoscopic = new CustomerColonoscopic();
		}
		return customerColonoscopic;
	}

	public void setCustomerColonoscopic(CustomerColonoscopic customerColonoscopic) {
		this.customerColonoscopic = customerColonoscopic;
	}

	@SuppressWarnings({ "unchecked" })
	private JSONObject toJson(Object obj) {
		try {
			JSONObject jsonObj = new JSONObject();
			JSONArray pain = new JSONArray();
			JSONArray medicalHistory = new JSONArray();
			JSONArray checkup = new JSONArray();
			jsonObj.put("p", pain);
			jsonObj.put("m", medicalHistory);
			jsonObj.put("c", checkup);
			getParentList().clear();
			for (Field field : obj.getClass().getDeclaredFields()) {
				JSONObject fieldJson = new JSONObject();
				if (field.isAnnotationPresent(Label.class)) {
					Label labelAnno = (Label) field.getAnnotation(Label.class);

					if (field.get(obj) != null) {
						fieldJson.put("d", field.getType().getName());
						fieldJson.put("n", field.getName());
						fieldJson.put("t", labelAnno.fieldType());
						fieldJson.put("lt", labelAnno.labelType());
						if (field.getType().getName().equals("java.math.BigDecimal")) {
							if (field.get(obj) != null
									&& ((BigDecimal) field.get(obj)).compareTo(BigDecimal.ZERO) == 1) {
								fieldJson.put("v", ((BigDecimal) field.get(obj)).toString());
								fieldJson.put("l", labelAnno.label());
							} else {
								fieldJson.put("v", null);
							}
						} else if (field.getType().getName().equals("java.lang.String")) {
							if (field.get(obj) != null && !((String) field.get(obj)).equals("")) {
								fieldJson.put("v", (String) field.get(obj));
								fieldJson.put("l", labelAnno.label());
							} else {
								fieldJson.put("v", null);
							}
						} else if (field.getType().getName().equals("java.util.Date")) {
							if (field.get(obj) != null) {
								fieldJson.put("v", (dateFormatter.format((Date) field.get(obj)).toString()));
								fieldJson.put("l", labelAnno.label());
							} else {

							}
						} else if (field.getType().getName().equals("int")) {
							if (field.get(obj) != null && ((int) field.get(obj)) != 0) {
								fieldJson.put("v", ((int) field.get(obj)));
								fieldJson.put("l", labelAnno.label());
								if (!labelAnno.answers().equals("")) {
									JSONArray arr = new JSONArray();
									for (String str : labelAnno.answers()) {
										arr.add(str);
									}
									fieldJson.put("a", arr);
								}
							} else {
								fieldJson.put("v", null);
							}
						} else if (field.getType().getName().equals("boolean")) {
							if (field.get(obj) != null && (boolean) field.get(obj) == true) {
								fieldJson.put("v", ((boolean) field.get(obj)));
								fieldJson.put("l", labelAnno.label());
							}
						}
						if (fieldJson.get("v") != null)
							if (fieldJson.get("lt").equals("m")) {
								medicalHistory.add(fieldJson);
							} else if (fieldJson.get("lt").equals("p")) {
								pain.add(fieldJson);
							} else if (fieldJson.get("lt").equals("c")) {
								checkup.add(fieldJson);
							}
						getParentList().add(fieldJson);
					}
				}
			}
			return jsonObj;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<JSONObject> getParentList() {
		if (parentList == null) {
			parentList = new ArrayList<>();
		}
		return parentList;
	}

	public void setParentList(List<JSONObject> parentList) {
		this.parentList = parentList;
	}

	public JSONObject getColonoJson() {
		return colonoJson;
	}

	public void setColonoJson(JSONObject colonoJson) {
		this.colonoJson = colonoJson;
	}

	public List<EndoscopyDetail> getEndoCopyDetialList() {
		return endoCopyDetialList;
	}

	public void setEndoCopyDetialList(List<EndoscopyDetail> endoCopyDetialList) {
		this.endoCopyDetialList = endoCopyDetialList;
	}

	public XrayDtl getXrayDtl() {

		if (xrayDtl == null) {
			xrayDtl = new XrayDtl();
		}
		return xrayDtl;
	}

	public void setXrayDtl(XrayDtl xrayDtl) {
		this.xrayDtl = xrayDtl;
	}

	public List<XrayDtl> getXrayDtlList() {
		if (xrayDtlList == null) {
			xrayDtlList = new ArrayList<>();
		}
		return xrayDtlList;
	}

	public void setXrayDtlList(List<XrayDtl> xrayDtlList) {
		this.xrayDtlList = xrayDtlList;
	}

	public XrayRequest getXrayRequestCustomer() {
		if (xrayRequestCustomer == null)
			xrayRequestCustomer = new XrayRequest();
		return xrayRequestCustomer;
	}

	public void setXrayRequestCustomer(XrayRequest xrayRequestCustomer) {
		this.xrayRequestCustomer = xrayRequestCustomer;
	}

	public JSONObject getJsonColono() {
		return jsonColono;
	}

	public void setJsonColono(JSONObject jsonColono) {
		this.jsonColono = jsonColono;
	}

	public String getDateNow() {
		Date date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
	}

	public String getDateString() {
		Date date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public List<CustomerAttachment> getXrayColonoAttachmens() {
		if (xrayColonoAttachmens == null) {
			xrayColonoAttachmens = new ArrayList<>();
		}
		return xrayColonoAttachmens;
	}

	public void setXrayColonoAttachmens(List<CustomerAttachment> xrayColonoAttachmens) {
		this.xrayColonoAttachmens = xrayColonoAttachmens;
	}

	public Ultrasound getUltrasound() {
		if (ultrasound == null)
			ultrasound = new Ultrasound();
		return ultrasound;
	}

	public void setUltrasound(Ultrasound ultrasound) {
		this.ultrasound = ultrasound;
	}

	public String getDisplayStatus() {
		if (displayStatus == null) {
			displayStatus = "block";
		}
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public XrayDtl getXrayDtlMood() {
		return xrayDtlMood;
	}

	public void setXrayDtlMood(XrayDtl xrayDtlMood) {
		this.xrayDtlMood = xrayDtlMood;
	}

	public InspectionController getInspectionController() {
		return inspectionController;
	}

	public void setInspectionController(InspectionController inspectionController) {
		this.inspectionController = inspectionController;
	}

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	public String getModelValue() {
		return modelValue;
	}

	public void setModelValue(String modelValue) {
		this.modelValue = modelValue;
	}

	public boolean isRenderable() {
		return renderable;
	}

	public void setRenderable(boolean renderable) {
		this.renderable = renderable;
	}

	public String changeUrl() {
		return "xray_request";
	}

	public List<EndoTemplate> getEndoTemplates() {
		if (endoTemplates == null)
			endoTemplates = new ArrayList<EndoTemplate>();
		return endoTemplates;
	}

	public void setEndoTemplates(List<EndoTemplate> endoTemplates) {
		this.endoTemplates = endoTemplates;
	}

	public EndoTemplate getCurrentEndoTemplate() {
		if (currentEndoTemplate == null)
			currentEndoTemplate = new EndoTemplate();
		return currentEndoTemplate;
	}

	public void setCurrentEndoTemplate(EndoTemplate currentEndoTemplate) {
		this.currentEndoTemplate = currentEndoTemplate;
	}

	public void newEndoTemplate() {
		currentEndoTemplate = new EndoTemplate();
		currentEndoTemplate.setStatus(Tool.ADDED);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:templateSection");
	}

	public void deleteEndotemplate() {
		currentEndoTemplate.setStatus(Tool.DELETE);
		saveEndoTemplate();
	}

	public String getImageName() {
		if (imageName == null)
			imageName = "/Hospital/resource/images/endo.jpg";
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImgSrc() {
		if (imgSrc == null)
			imgSrc = "";
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public void imageChanged() {
		getImageName();
		if ("/Hospital/resource/images/endo.jpg".equals(imageName))
			setImageName("/Hospital/resource/images/endo1.jpg");
		else
			setImageName("/Hospital/resource/images/endo.jpg");
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:lilimage");
	}

	public int getSliderCursor() {
		return sliderCursor;
	}

	public void setSliderCursor(int sliderCursor) {
		this.sliderCursor = sliderCursor;
	}

	// EndRegion
}
