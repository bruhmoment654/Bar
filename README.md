# Реализованные фичи

## Главный экран

Основной экран создан в соответствии с дизайном. Если в базе данных отсутствуют товары, показывается экран с логотипом, если есть хоть один товар, показывается второй экран с товарами. 
Однако, на нем немного кривая разметка, не было времени чинить, элементы добавляются в один столбик, второй остается пустым.

![Screenshot](https://github.com/bruhmoment654/Bar/raw/master/Screenshots/ImagePicker.png)


## Экран добавления новых коктейлей

На данной активити реализован почти весь дизайн, добавлен выбор фото из галереи с помощью registerForActivityResult. Валидацию не успел добавить, но коктейль не добавится в базу данных, если не вписать обязательное поле - названиею
Выбору ингредиентов через диалог не успел добавить логику, есть только разметка.

## Хранение данных 

Все коктейли хранятся в базе данных, реализована с помощью Room

## Архитектура

Архитектуру сделал MVI, чтобы лучше в ней освоиться

