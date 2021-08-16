$(document).ready(() => {
  $("#search-help").hover(function () {
    $(".help").show(200);
  });
  $("#search-help").mouseout(function () {
    $(".help").hide(200);
  });

  const BASE_URL = "https://localhost:5001/";

  $(".search-btn").click(function () {
    $(".search-area .error").hide(200);
    let q = $("#search").val();
    if (q) {
      addDocs(q);
    } else {
      $(".search-area .error").show(200);
    }
  });

  function addDocs(q) {
    $(".docs-container").children().hide(100);
    $.get(BASE_URL + q)
      .then((docs) => {
        if (!docs || !docs.length) throw "Empty";

        docs.forEach((doc) => {
          $(".docs-container").append(`
                <div class='doc'>
                  <h4 class='doc-title'>${doc}</h4>
                </div>
              `);
        });
      })
      .catch((err) => {
        $(".docs-container .error").show(200);
      });
  }
});
