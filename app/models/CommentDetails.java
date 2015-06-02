/**
 * 
 */
package models;

/**
 * @author Ravi
 *
 */
public class CommentDetails {
	private Integer ticketId;
	private String comment;
	
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
 
}
