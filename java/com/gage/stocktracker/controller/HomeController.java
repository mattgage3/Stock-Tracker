package com.gage.stocktracker.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gage.stocktracker.services.StockDataService;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {

		model.addAttribute("companyName", StockDataService.companyName);
		model.addAttribute("symbol", StockDataService.companyTicker);
		model.addAttribute("latestTime", StockDataService.tradeDay);
		model.addAttribute("open", StockDataService.openPrice);
		model.addAttribute("latestPrice", StockDataService.latestPrice);
		model.addAttribute("high", StockDataService.highPrice);
		model.addAttribute("low", StockDataService.lowPrice);

		return "index";	
	}
	
	@PostMapping("/")
  	public String input(Model model , @RequestParam("stock") String stock) throws IOException, InterruptedException{
		StockDataService.fetchStockData(stock);
		model.addAttribute("companyName", StockDataService.companyName);
		model.addAttribute("symbol", StockDataService.companyTicker);
		model.addAttribute("latestTime", StockDataService.tradeDay);
		model.addAttribute("open", StockDataService.openPrice);
		model.addAttribute("latestPrice", StockDataService.latestPrice);
		model.addAttribute("high", StockDataService.highPrice);
		model.addAttribute("low", StockDataService.lowPrice);

		return "index";
    }
}

