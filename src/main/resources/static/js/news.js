function news() {
    $.ajax({
        type: "GET",
        url: "/newsApi",


    }).done(function (result) {
        console.log(result)

        let itemTrial = result['items'];
        console.log(itemTrial);

        itemTrial.forEach((a) => {
            let itemDption= a["description"];
            let itemTitles= a["title"];
            let itemLink=a["link"];

            console.log(itemTitles);
         let itemTitle = `<div><a href=${itemLink}> ${itemTitles} </a></div> 
                           <div class="description"> ${itemDption} </div>`;

  $('#itemTrial').append(itemTitle);
        });


        const items = result.items;

    let table = '<table>';
        for (var i = 0; i < 10; i++) {
            let title = items[i].title;
            let links = items[i].link;
            let description = items[i].description;
           table = table + "<tr class='newSpace'>";    // <tr> : 행추가.
            table = table + "<td >"+(i+1)+ ". "+ "<a href="+links+">" + title + "</a></td>";
            table +="</tr>";
            table = table + "<tr>";
            table = table + "<td>" + description + "</td>";
            table +="</tr>";

        }
        table +="</table>";
        document.getElementById("news").innerHTML = table;


    }).fail(function (jqXHR) {
        console.log(jqXHR);
        console.log("실패")
    }).always(function () {
        console.log(" 시행중")
    })
}

news();