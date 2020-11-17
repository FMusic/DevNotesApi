function loadNotebooks() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/notebooks');
    xhr.responseType = 'json';
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4){
            const data = this.response;
            data.forEach(function(item){
                var s = document.getElementById('notebooks');
                s.innerHTML += '<a class="dropdown-item" href="#" onClick="loadSections('+ item.id + ')">'+ item.name +'</a>'
                console.log(item)
            });
        }
    }
    xhr.send();
}

function loadSections(id){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/notebooks/' + id);
    xhr.responseType = 'json';
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            const data = this.response;
            data.sections.forEach(function(item){
                var s = document.getElementById('sections');
                s.innerHTML += '<li class="list-group-item"> <a href="#" onClick="loadText(' + item.id + ')">' + item.name + '</a></li>';
                console.log(item)
                var st = document.getElementById('section-text');
                st.innerHTML = item.text;
            });
        }
    }
    xhr.send();
}