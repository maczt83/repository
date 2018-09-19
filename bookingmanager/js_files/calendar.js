$('#calendar').fullCalendar({
    schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives'
});
$(function() { // dom ready

    var todayDate = moment().startOf('day');
    var YESTERDAY = todayDate.clone().subtract(1, 'day').format('YYYY-MM-DD');
    var TODAY = todayDate.format('YYYY-MM-DD');
    var TOMORROW = todayDate.clone().add(1, 'day').format('YYYY-MM-DD');

    $('#calendar').fullCalendar({
        resourceAreaWidth: 230,
        editable: false,
        aspectRatio: 2,
        scrollTime: '06:00',
        header: {
            left: 'promptResource today prev,next',
            center: 'title',
            right: 'timelineSevenDays,timelineFourteenDays'
        },
        defaultView: 'timelineSevenDays',
        views: {
            timelineSevenDays: {
                type: 'timeline',
                duration: { days: 7 }
            },
            timelineFourteenDays: {
                type: 'timeline',
                duration: { days: 14 }
            }
        },
        resourceLabelText: 'Rooms',
        resources:  calendarResources,
        events: calendarEvents,
        slotDuration: '24:00:00',
        eventClick : function(callEvent){
            window.location.href = "/booking/" + callEvent.bookingId;
        }
    });

});

// readjust sizing after font load
$(window).on('load', function() {
    $('#calendar').fullCalendar('render');
});
