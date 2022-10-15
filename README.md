# LeprechaunsSpendings
A tool for managing family budget

# Backend implementation
The first requirement for the backend app includes following operations:
```
GET spendings by month
/spendings?months=[]

{
    [
        {
            monthYear: string, //MM-yyyy
            spendings: [
                {
                    id: string
                    author: string, // (id/name?)
                    money: Money, // (amount, currency)
                    type: SpendingType,
                    title: string,
                    description: string,
                    date: Date
                }
            ]
        },
        {...}
    ]
}
```
```
POST /spendings/
request 
                {
                    author: string, // (id/name?)
                    money: Money, // (amount, currency)
                    type: SpendingType,
                    title: string,
                    description: string,
                    date: Date
                }

response 
                {
                    id: string
                    author: string, // (id/name?)
                    money: Money, // (amount, currency)
                    type: SpendingType,
                    title: string,
                    description: string,
                    date: Date
                }
```
```
PUT /spendings/{id}
```
```
DELETE /spendings/{id}
empty body
```
