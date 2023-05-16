# language: ru

@TestBasket
Функционал: Тестирование корзины

  @all
  Сценарий: Проверка удаления всех элементов из корзины
    Дано пользователь открывает страницу магазина
    Когда добавляем товары в корзину
    И удаляем товар из корзины
    Тогда количество элементов в корзине равно 0