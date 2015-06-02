package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Ticker;

import models.*;
import play.*;
import play.mvc.*;
import play.api.libs.json.*;
import play.data.DynamicForm;
import play.data.Form;
import views.html.*;

public class Application extends Controller {

	// Instead of using Database Table here I am usin List for storing the
	// tickets
	// This hashmap is used for Ticket Lookup/Search in order to replicate
	// primary key in DB table
	private static Map<Integer, TicketDetails> ticketMap = new HashMap<Integer, TicketDetails>();
	private static Map<Integer, List<CommentDetails>> commentMap = new HashMap<Integer, List<CommentDetails>>();

	public static Result index() {
		return ok(main.render());
	}
	//SEARCH logic
	public static Result searchTicket() {
		// Status status = null;
		DynamicForm form = Form.form().bindFromRequest();
		String ticketId = form.get("ticketId");
		Integer id = Integer.parseInt(ticketId);
		if (ticketId != null) {
			// Search for ticket id
			TicketDetails ticketDetails = ticketMap.get(id);
			String status = "";
			if(ticketDetails!=null)
				status = ticketDetails.getStatus();
			List<CommentDetails> commentList = commentMap.get(id);
			return ok(createViewTicket.render(ticketDetails, commentList,status));
		} else {
			return ok(createViewTicket.render(null, null,null));
		}

	}
	//SAVE logic
	public static Result saveTicket() {
		DynamicForm form = Form.form().bindFromRequest();
		String ticketId = form.get("ticketId");
		String title = form.get("ticketTitle");
		String assignedTo = form.get("assignedTo");
		String createdBy = form.get("createdBy");
		String status = form.get("selectStatus");
		String description = form.get("description");

		TicketDetails ticketDetails = new TicketDetails();

		Integer id = Integer.parseInt(ticketId);
		ticketDetails.setTicketId(id);
		ticketDetails.setTitle(title);
		ticketDetails.setAssignedTo(assignedTo);
		ticketDetails.setDescription(description);
		ticketDetails.setReportedBy(createdBy);
		ticketDetails.setStatus(status);
		ticketMap.put(id, ticketDetails);

		return ok(main.render());
	}

	public static Result createTicket() {
		TicketDetails ticketDetails = new TicketDetails();
		return ok(createViewTicket.render(ticketDetails, null,null));
	}
	//SAVE COMMENT logic
	public static Result addComment() {
		DynamicForm form = Form.form().bindFromRequest();
		String ticketId = form.get("ticketId");
		String comment = form.get("commentText");
		Integer id = Integer.parseInt(ticketId);
		String status ="";

		CommentDetails commentObj = new CommentDetails();
		commentObj.setTicketId(id);
		commentObj.setComment(comment);

		if (commentMap.containsKey(id)) {
			commentMap.get(id).add(commentObj);
		} else {
			List<CommentDetails> list = new ArrayList<>();
			list.add(commentObj);
			commentMap.put(id, list);
		}

		TicketDetails ticketDetails = ticketMap.get(id);
		if(ticketDetails!=null)
			 status = ticketDetails.getStatus();
		List<CommentDetails> commentList = commentMap.get(id);
		return ok(createViewTicket.render(ticketDetails, commentList,status));
	}

}
