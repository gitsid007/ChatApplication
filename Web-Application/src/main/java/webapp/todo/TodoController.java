package webapp.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import webapp.todo.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	TodoService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public String list(ModelMap model)
	{
		model.addAttribute("todos", service.retrieveTodos(retrieveLoggedinUserName()));
		return "list";
	}
	
	@RequestMapping(value="/addlist", method= RequestMethod.GET)
	public String showlist(ModelMap model)
	{
		model.addAttribute("todo", new Todo(0, retrieveLoggedinUserName(), "", new Date(), false));
		return "todo";
	}
	
	
	@RequestMapping(value="/addlist", method= RequestMethod.POST)
	public String addlist(ModelMap model, @Valid Todo todo, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		
		model.clear();
		service.addTodo(retrieveLoggedinUserName(), todo.getDesc(), new Date(), false);
		return "redirect:list";
	}
	
	
	private String retrieveLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
	@RequestMapping(value="/updatelist", method= RequestMethod.GET)
	public String updatelist(ModelMap model, @RequestParam int id)
	{
		Todo todo = service.retrieveTodo(id);
		model.addAttribute("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="/updatelist", method= RequestMethod.POST)
	public String updatelist(ModelMap model, @Valid Todo todo, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		todo.setUser(retrieveLoggedinUserName());
		service.updateTodo(todo);
		
		model.clear();
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/deletelist", method= RequestMethod.GET)
	public String removelist(ModelMap model, @RequestParam int id)
	{
		service.deleteTodo(id);
		model.clear();
		return "redirect:list";
	}
	
	
	}
