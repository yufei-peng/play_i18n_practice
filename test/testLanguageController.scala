import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder

import play.api.test.FakeRequest
import play.api.mvc._
import play.api.test._

class testLanguageController extends PlaySpecification with Results {

  " request language in header" should {

    val application: Application = GuiceApplicationBuilder().build()

    val i18nController = application.injector.instanceOf[controllers.I18nController]

    "return english" in {

      val request = FakeRequest("GET", "/test").withHeaders(("accept-language", "en"))

      val response = i18nController.testLanguage.apply(request)

      status(response) mustEqual 200
      contentAsString(response) mustEqual "This is English"

    }

    "return traditional chinese" in {

      val request = FakeRequest("GET", "/test").withHeaders(("accept-language", "zh-TW"))

      val response = i18nController.testLanguage.apply(request)

      status(response) mustEqual 200
      contentAsString(response) mustEqual "我是繁體字"

    }

    "return simplified chinese" in {

      val request = FakeRequest("GET", "/test").withHeaders(("accept-language", "zh-CN"))

      val response = i18nController.testLanguage.apply(request)

      status(response) mustEqual 200

      contentAsString(response) must be equalTo "我是简体字"

    }

  }

}
