function renderUsers(users){
  console.log('render');
  $('#user-list').empty();
  users.forEach(function(user){ $('#user-list').append($('<li/>', { html: user })); });
}

function getUsers(){
  $.get('/users', renderUsers);
}

function handleError(xhr){
  $('#error').text(xhr.statusText + ": " + xhr.responseText);
}

function addUser(){
  $.post('/add-user', { user: $('#name').val() }, renderUsers).fail(handleError);
}

$(function(){ getUsers(); });
