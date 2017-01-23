package hospital.businessentity;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private String message;
	List<Msg> messages;

	public Message() {
		messages = new ArrayList<Msg>();
		for (int index = 0; index <= 10000; index++) messages.add(new Msg());
		messages.set(0, new Msg("Амжилттай нэвтэрлээ.", Tool.msgSuccess));
		messages.set(1, new Msg("Нэвтрэх нэр эсвэл нууц үг буруу байна.", Tool.msgWarning));
		messages.set(2, new Msg("Байгууллагын нэрийг оруулна уу!", Tool.msgWarning));
		messages.set(3, new Msg("И-Мэйл оруулаагүй байна!", Tool.msgWarning));
		messages.set(4, new Msg("Утасны дугаараа орууулаагүй байна!", Tool.msgWarning));
		messages.set(5, new Msg("Хаяг дутуу байна!", Tool.msgWarning));
		messages.set(6, new Msg("Logo оруулаагүй байна!", Tool.msgWarning));
		messages.set(7, new Msg("И-мэйл буруу оруулсан байна!", Tool.msgWarning));
		messages.set(8, new Msg("Хэрэглэгчийн нэвтрэх нэр давхардсан байна!", Tool.msgWarning));
		messages.set(9, new Msg("Хэрэглэгчийн нэрийг бөглөөгүй байна!", Tool.msgWarning));
		messages.set(10, new Msg("Хэрэглэгчийн нэвтрэх нэрийг бөглөөгүй байна!", Tool.msgWarning));
		messages.set(11, new Msg("Хэрэглэгчийн нууц үгийг бөглөөгүй байна!", Tool.msgWarning));
		messages.set(12, new Msg("Хэрэглэгчийн нууц үгийг давтан оруулаагүй байна!", Tool.msgWarning));
		messages.set(13, new Msg("Хэрэглэгчийн нууц үгийг давтан оруулахдаа буруу оруулсан байна!", Tool.msgWarning));
		messages.set(14, new Msg("Хэрэглэгчийн хандах эрхийг тохируулаагүй байна!", Tool.msgWarning));
		messages.set(15, new Msg("Таны оруулсан эмчилгээний нэр давхардсан байна", Tool.msgWarning));
		messages.set(16, new Msg("Ажил дуусах цаг эхлэх цагаас бага байх ёстой!", Tool.msgWarning));
		messages.set(17, new Msg("Цайны дуусах цаг эхлэх цагаас бага байх ёстой!", Tool.msgWarning));
		messages.set(18, new Msg("Бүтэн сайнд ажил эхлэх цаг нь дуусахаас өмнүүр байж болохгүй!", Tool.msgWarning));
		messages.set(19, new Msg("Хагас сайнд ажил эхлэх цаг нь дуусахаас өмнүүр байж болохгүй!", Tool.msgWarning));
		messages.set(20, new Msg("Ажилтан сонгоогүй байна!", Tool.msgWarning));
		messages.set(21, new Msg("Эхлэх хугацаа дуусах хугацаанаас хойгуур байж болохгүй!", Tool.msgWarning));
		messages.set(22, new Msg("Эдийн засгийн хуанли өдрөөр тохируулах цагийн эхлэх хугацаа нь дуусах хугацаанаас хойгуур байна.", Tool.msgWarning));
		messages.set(23, new Msg("Төлсөн дүн нийт төлөх дүнгээс хэтэрсэн байна.", Tool.msgWarning));
		messages.set(24, new Msg("Тухайн харилцагч дээр тооцоо байхгүй байна.", Tool.msgWarning));
		messages.set(25, new Msg("Төлсөн дүн төлөх дүнгээс хэтэрсэн байна.", Tool.msgWarning));
		messages.set(26, new Msg("Төлөх дүнгээ оруулаагүй байна.", Tool.msgWarning));
		messages.set(27, new Msg("Үйлчлүүлэгч сонгоогүй байна.", Tool.msgWarning));
		messages.set(28, new Msg("Харилцагч сонгоогүй байна.", Tool.msgWarning));
		messages.set(29, new Msg("Таны хандах эрх хүрэхгүй байна!!!", Tool.msgError));
		messages.set(30, new Msg("Тухайн хүсэлтыг утсгах засах боломжгүй байна!", Tool.msgWarning));
		messages.set(31, new Msg("Сонгосон Оношилгоо байна.", Tool.msgWarning));
		messages.set(32, new Msg("Төлбөрийн үлдэгдэл байхгүй байна.", Tool.msgWarning));
		messages.set(33, new Msg("Тухайн хэрэглэгч дээр цаг давхардсан байна.", Tool.msgWarning));
		messages.set(34, new Msg("Хуучин нууц үг буруу байна", Tool.msgWarning));
		messages.set(35, new Msg("Шинэ нууц үг баталгаажуулахдаа буруу оруулсан байна", Tool.msgWarning));
		messages.set(36, new Msg("Нууц үг амжилттай өөрчиллөө", Tool.msgSuccess));
		messages.set(37, new Msg("Нэр оруулаагүй байна", Tool.msgWarning));
		messages.set(38, new Msg("Хамааралтай оншилгоонуудыг тохируулаагүй байна.", Tool.msgWarning));
		messages.set(39, new Msg("Хамааралтай оншилгоонуудыг буруу тохируулсан байна.", Tool.msgWarning));
		messages.set(40, new Msg("Эмийн нэрийг оруулна уу", Tool.msgWarning));
		messages.set(41, new Msg("Эмийн олон улсын нэрийг оруулна уу", Tool.msgWarning));
		messages.set(42, new Msg("Эмийн код хоосон байна", Tool.msgWarning));
		messages.set(43, new Msg("", Tool.msgSuccess));
		messages.set(44, new Msg("", Tool.msgSuccess));
		messages.set(45, new Msg("", Tool.msgSuccess));
		messages.set(46, new Msg("", Tool.msgSuccess));
		messages.set(47, new Msg("Амжилттай болчоод байгаан бишүү", Tool.msgSuccess));
		messages.set(48, new Msg("Анхааруулга гараад байгаан бишүү", Tool.msgWarning));
		messages.set(49, new Msg("Алдаа гарлаа.", Tool.msgError));
		
		messages.set(50, new Msg("Барааны нэр хоосон байна", Tool.msgWarning));
		messages.set(51, new Msg("Бараа давхцсан байна", Tool.msgWarning));
		messages.set(52, new Msg("Эмчилгээний төрлийн нэр хоосон байна", Tool.msgWarning));
		messages.set(53, new Msg("Эмчилгээний нэр хоосон байна", Tool.msgWarning));
		messages.set(54, new Msg("Оношилгооны төрлийн нэр хоосон байна", Tool.msgWarning));
		messages.set(55, new Msg("Оношилгооны нэр хоосон байна", Tool.msgWarning));
		messages.set(56, new Msg("Өрөөний дугаар хоосон байна", Tool.msgWarning));
		messages.set(57, new Msg("Дотоод үнэ хоосон байна", Tool.msgWarning));
		messages.set(58, new Msg("Дотоод үнэ ашиглах огноо хоосон байна", Tool.msgWarning));
		messages.set(59, new Msg("Гадаад үнэ хоосон байна", Tool.msgWarning));
		messages.set(60, new Msg("Картын дугаар хоосон байна", Tool.msgWarning));
		messages.set(61, new Msg("Үйлчлүүлэгчийн овог хоосон байна", Tool.msgWarning));
		messages.set(62, new Msg("Үйлчлүүлэгчийн нэр хоосон байна", Tool.msgWarning));
		messages.set(63, new Msg("Үйлчлүүлэгчийн утасны дугаар хоосон байна", Tool.msgWarning));
		messages.set(64, new Msg("Буруу регистрийн дугаар оруулсан байна", Tool.msgWarning));
		messages.set(65, new Msg("Уг регистрийн дугаартай үйлчлүүлэгч бүртгэгдсэн байна", Tool.msgWarning));
		messages.set(66, new Msg("Барааны үнийг оруулна уу", Tool.msgWarning));
		messages.set(67, new Msg("Эмчилгээний үнэ алдаатай байна", Tool.msgWarning));
		messages.set(68, new Msg("Систем хэрэглэх хугацаа дууссан байна.", Tool.msgWarning));
		messages.set(69, new Msg("", Tool.msgWarning));
		messages.set(70, new Msg("Кабинет сонгоогүй байна", Tool.msgWarning));
		messages.set(71, new Msg("Ажилтны ID буруу байна", Tool.msgWarning));
		messages.set(72, new Msg("Ажилны овог хоосон байна", Tool.msgWarning));
		messages.set(73, new Msg("Ажилтны нэр хоосон байна", Tool.msgWarning));
		messages.set(74, new Msg("Ажилтны утасн дугаар хоосон байна", Tool.msgWarning));
		messages.set(75, new Msg("Зэрэг сонгоогүй байна", Tool.msgWarning));
		messages.set(76, new Msg("", Tool.msgWarning));
		messages.set(77, new Msg("Кабинетийн төрөл сонгоогүй байна", Tool.msgWarning));
		messages.set(78, new Msg("Кабинетийн нэр хоосон байна", Tool.msgWarning));
		messages.set(79, new Msg("Өрөөний дугаар хоосон байна", Tool.msgWarning));
		messages.set(80, new Msg("Үзлэгийн хугацаа хоосон байна", Tool.msgWarning));
		messages.set(81, new Msg("Сонгогдлоо", Tool.msgSuccess));
		messages.set(82, new Msg("Үнэ ашиглах огноог оруулна уу!", Tool.msgWarning));
		messages.set(83, new Msg("Үнэ ашиглах огоо системийн цагаас хойш байх ёстой", Tool.msgWarning));
		//Төлбөр төлөлттөй холбоотой алдаанууд
		messages.set(84, new Msg("Төлөх дүн байхгүй байна.", Tool.msgWarning));
		messages.set(85, new Msg("", Tool.msgSuccess));
		messages.set(86, new Msg("", Tool.msgSuccess));
		messages.set(87, new Msg("", Tool.msgSuccess));
		messages.set(88, new Msg("", Tool.msgSuccess));
		messages.set(89, new Msg("Амжилттай захиаллаа", Tool.msgSuccess));
		messages.set(90, new Msg("", Tool.msgSuccess));
		messages.set(91, new Msg("", Tool.msgSuccess));
		messages.set(92, new Msg("", Tool.msgSuccess));
		messages.set(93, new Msg("License - ийн нууд үг оруулаагүй байна.", Tool.msgWarning));
		messages.set(94, new Msg("License - ийн нууд үг даван оруулахдаа буруу оруулсан байна.", Tool.msgWarning));
		messages.set(95, new Msg("Цаг давхардсан байна.", Tool.msgSuccess));
		messages.set(96, new Msg("Эмчилгээ/оношилгоо/ сонгоно уу!", Tool.msgWarning));
		messages.set(97, new Msg("Сонгогдсон оношилгоо байна", Tool.msgWarning));
		messages.set(98, new Msg("Амжилттай устгалаа", Tool.msgSuccess));
		messages.set(99, new Msg("Амжилттай хадгаллаа", Tool.msgSuccess));
		messages.set(100, new Msg("Үзлэг  дууслаа", Tool.msgSuccess));
	}

	public String getMessage() {
		return message;
	}

	public Msg getMessage(int index) {
		return messages.get(index);

	}

	public void setMessage(String message) {
		this.message = message;
	}

}
