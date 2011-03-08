<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<jsp:include page="jsp/layout/header_menu.jsp" />
      <div id="main">
        <!-- begin: #col1 - first float column -->
        <div id="col1" role="complementary">
          <div id="col1_content" class="clearfix">
          </div>
        </div><!-- end: #col1 -->
        <!-- begin: #col3 static column -->
        <div id="col3" role="main">
          <div id="col3_content" class="clearfix">
          <div class="header_section">
          <span class="name_section">Add New Room</span>
          <div class="right">
            </div>
          </div>
          <div>
		 <form method="post" action="findAllRooms.action" class="yform" role="application">
            <fieldset>
              <legend>
              <label for="room_name_id">Name Room: <sup title="This field is mandatory.">*</sup></label>
              <input id="room_name_id" class="required" name="room.name" type="text" value="Enter Room Name" /></legend>
             <div class="c50l">
             <div class="subcolumns">
             <span>Save room to define prices or minimum stay</span>
             </div>

                <div class="type-text" >
               <label for="roomtype_id">Room Type:&nbsp;&nbsp;&nbsp;&nbsp;(Start typing the name of the room)</label> 
               <input  type="text"  id="roomtype_id"  name="room.roomType" />
                    </div> 
              <div class="subcolumns type-text">
      <label for="price_room_id">Price <sup title="This field is mandatory.">*</sup></label><input type="text" class="required small_input" id="price_room_id" name="room.price" value="30" />
             <span>&nbsp;&euro;</span>
              </div>
              <div class="subcolumns type-text">
      		<label for="max_guests_id">Max Guests: <sup title="This field is mandatory.">*</sup></label>
				 <input id="max_guests_id" type="text" class="required small_input" name="room.maxGuests" value="1" />
		     </div>
			 <div class="subcolumns">
              &nbsp;
              </div>

            <div class="subcolumns type-text">
      		<label for="notes_id">Note:</label>&nbsp;
      		<textarea id="notes_id" name="room.notes" rows="5" cols="60"></textarea>
      		</div>
      		<div class="subcolumns">
              &nbsp;
              </div>
            <div class="type-button">
            <button class="btn_save btn_add_room">SAVE</button>
            <button class="btn_delete">CANCEL</button>
            </div>
            
            </div>
              </fieldset>
           </form>        
		</div>        
          </div>
<jsp:include page="jsp/layout/footer.jsp" />     