var $ = window.$;
function deleteImages(){
  var csrf = $('#__anti-forgery-token').val(),
      $selectInputs = $('input:checked'),
      selectedIds = $selectInputs.map(function(){
        return $(this).attr('id');
      }),
      data = { 'csrf-token': csrf, names: selectedIds };

  if(selectedIds.length < 1){
    alert('no images selected');
  } else {

    $.ajax({
      url: '/delete',
      method: 'POST',
      headers: { 'X-CSRF-token': csrf },
      data: { names: Array.prototype.slice.apply(selectedIds) },
      dataType: 'json'
    }).then(function(res){
      var errors = $('<ul>');
      res.forEach(function(o){
        var element;
        if(o.status === 'ok'){
          element = document.getElementById(o.name);
          console.log(element);
          $(element).parent().remove();
        } else {
          errors.append($('<li>',
                          {html: 'failed to remove ' + o.name + ': ' + o.status}));
        }
      });

      if(errors.length > 0){
        $('#error').empty().append(errors);
      }
    });
  }
}
$(function(){
  $('#delete').click(deleteImages);
});
