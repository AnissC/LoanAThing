
var k = [38, 38, 40, 40, 37, 39, 37, 39, 66, 65],
    n = 0;
$(document).keydown(function (e) {
    if (e.keyCode === k[n++]) {
        if (n === k.length) {
            userWhoBorrowedChart.segments[0].value = Math.floor(Math.random() * 100);
            userWhoBorrowedChart.segments[1].value = Math.floor(Math.random() * 100);
            userWhoBorrowedChart.update()
            nbAdvertMonthChart.datasets[0].bars[0].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[1].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[2].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[3].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[4].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[5].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[6].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[7].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[8].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[9].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[10].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.datasets[0].bars[11].value = Math.floor(Math.random() * 100);
            nbAdvertMonthChart.update()
            myLineChart.datasets[0].points[0].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[1].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[2].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[3].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[4].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[5].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[6].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[7].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[8].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[9].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[10].value = Math.floor(Math.random() * 100);
            myLineChart.datasets[0].points[11].value = Math.floor(Math.random() * 100);
            myLineChart.update();
            nbAdvertByCategoryChart.segments[0].value = Math.floor(Math.random() * 100);
            nbAdvertByCategoryChart.segments[1].value = Math.floor(Math.random() * 100);
            nbAdvertByCategoryChart.segments[2].value = Math.floor(Math.random() * 100);
            nbAdvertByCategoryChart.segments[3].value = Math.floor(Math.random() * 100);
            nbAdvertByCategoryChart.segments[4].value = Math.floor(Math.random() * 100);
            nbAdvertByCategoryChart.segments[5].value = Math.floor(Math.random() * 100);
            nbAdvertByCategoryChart.segments[6].value = Math.floor(Math.random() * 100);
            nbAdvertByCategoryChart.update();

            var $nbAdvert = $("#nbAdvert"),
                valueNbAdvert = Math.floor(Math.random() * 1000);

            $({percentage: 0}).stop(true).animate({percentage: valueNbAdvert}, {
                duration : 2000,
                step: function () {
                    var percentageVal = Math.round(this.percentage);
                    $nbAdvert.text(percentageVal);
                }
            }).promise().done(function () {
                $nbAdvert.text(valueNbAdvert);
            });

            var $nbLoan = $("#nbLoan"),
                valueNbLoan = Math.floor(Math.random() * 1000);

            $({percentage: 0}).stop(true).animate({percentage: valueNbLoan}, {
                duration : 2000,
                step: function () {
                    var percentageVal = Math.round(this.percentage);
                    $nbLoan.text(percentageVal);
                }
            }).promise().done(function () {
                $nbLoan.text(valueNbLoan);
            });

            var $nbReview = $("#nbReview"),
                valueNbReview = Math.floor(Math.random() * 1000);

            $({percentage: 0}).stop(true).animate({percentage: valueNbReview}, {
                duration : 2000,
                step: function () {
                    var percentageVal = Math.round(this.percentage);
                    $nbReview.text(percentageVal);
                }
            }).promise().done(function () {
                $nbReview.text(valueNbReview);
            });

            var $currentLoan = $("#currentLoan"),
                valueCurrentLoan = Math.floor(Math.random() * 100);

            $({percentage: 0}).stop(true).animate({percentage: valueCurrentLoan}, {
                duration : 2000,
                step: function () {
                    var percentageVal = Math.round(this.percentage);
                    $currentLoan.text(percentageVal);
                }
            }).promise().done(function () {
                $currentLoan.text(valueCurrentLoan);
            });

            var $nbUserBan = $("#nbUserBan"),
                valueUserBan = Math.floor(Math.random() * 100);

            $({percentage: 0}).stop(true).animate({percentage: valueUserBan}, {
                duration : 2000,
                step: function () {
                    var percentageVal = Math.round(this.percentage);
                    $nbUserBan.text(percentageVal);
                }
            }).promise().done(function () {
                $nbUserBan.text(valueUserBan);
            });

            var $nbAdvertSuspended = $("#nbAdvertSuspended"),
                valueNbAdvertSuspended = Math.floor(Math.random() * 100);

            $({percentage: 0}).stop(true).animate({percentage: valueNbAdvertSuspended}, {
                duration : 2000,
                step: function () {
                    var percentageVal = Math.round(this.percentage);
                    $nbAdvertSuspended.text(percentageVal);
                }
            }).promise().done(function () {
                $nbAdvertSuspended.text(valueNbAdvertSuspended);
            });

            return false;
        }
    }
    else {
        n = 0;
    }
});