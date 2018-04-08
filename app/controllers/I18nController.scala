package controllers

import javax.inject.Inject

import play.api.i18n.{Lang, Langs, MessagesApi}
import play.api.mvc.{ MessagesAbstractController, MessagesControllerComponents }

class I18nController @Inject() (
                                 mcc:MessagesControllerComponents,
                                 langs:Langs,
                                 messageApi:MessagesApi,
                               ) extends MessagesAbstractController(mcc) {

  def testLanguage = Action { implicit request =>

    val lang: Lang = request.acceptLanguages.head

    val message: String = messageApi("test.message")(lang)

    Ok(message)

  }

}
