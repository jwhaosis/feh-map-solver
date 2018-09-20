package application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

	@PostMapping("/api/search")
	public String getSearchResultViaAjax(@RequestParam String search) {
		return search;

	}

}
