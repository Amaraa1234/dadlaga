users = [
  {:user, "Бат", 25},
  {:user, "Болд", 15},
  {:user, "Сараа", 19},
  {:user, "Дулмаа", 12}
]

adults = Enum.filter(users, fn
  {:user, _name, age} when age >= 18 -> true
  _ -> false
end)

adult_names = Enum.map(adults, fn {:user, name, _age} -> name end)

IO.inspect(adult_names)
