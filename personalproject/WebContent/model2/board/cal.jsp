<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>출석체크</title>
<link href='/assets/demo-to-codepen.css' rel='stylesheet' />
  <style>
    html, body {
      margin: 0;
      padding: 0;
      font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
      font-size: 14px;
    }
    #calendar {
      max-width: 900px;
      margin: 40px auto;
    } 
    .fc-bg table tbody tr td.fc-day.fc-widget-content.fc-fri.fc-today {
    	background-size: 128px 104px;
    	background-image: url("../../img/sad.jpg");
    }
  </style>
<link href='https://unpkg.com/@fullcalendar/core@4.3.1/main.min.css' rel='stylesheet' />
  <link href='https://unpkg.com/@fullcalendar/daygrid@4.3.0/main.min.css' rel='stylesheet' />
  <link href='https://unpkg.com/@fullcalendar/timegrid@4.3.0/main.min.css' rel='stylesheet' />
<script src='/assets/demo-to-codepen.js'></script>
<script src='https://unpkg.com/@fullcalendar/core@4.3.1/main.min.js'></script>
  <script src='https://unpkg.com/@fullcalendar/interaction@4.3.0/main.min.js'></script>
  <script src='https://unpkg.com/@fullcalendar/daygrid@4.3.0/main.min.js'></script>
  <script src='https://unpkg.com/@fullcalendar/timegrid@4.3.0/main.min.js'></script>
  <script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
      selectable: true,
      header: {
        left: 'prev,next',
        center: 'title',
        right: 'today, dayGridMonth'
      },
      dateClick: function(info) {
    	  var dateObj = info.event;
    	  var op = "width=550, height=550, left=750, top=150";
		    open("infocalForm.do","",op)
      }
    });
    calendar.render();
  });
</script>
</head>
<body>
  <div id='calendar'></div>
</body>
</html>
