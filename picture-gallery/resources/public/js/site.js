function colorStr(color){
  return 'rgb(' + color[0] + ", " + color[1] + ", " + color[2] + ')';
}

function setColor(div, colors){
  var bgColor = colors[0],
      textColor = colors[1];
  console.log(div);
  div.css('background-color', colorStr(bgColor));
  div.find('a').css('color', colorStr(textColor));
}

$(function(){
  $('.thumbnail').each(function(){
    var $div = $(this),
        url = $div.find('img').attr('src'),
        thumbColors = new AlbumColors(url);

    thumbColors.getColors(function(colors){
      setColor($div, colors);
    });
  });
});
