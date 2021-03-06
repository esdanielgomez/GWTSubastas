isc.DataSource.create({
    allowAdvancedCriteria:true,
    ID:"QuartzJobs",
    fields:[
        {
            name:"group",
            type:"string",
            required:true,
            validators:[
            ],
            primaryKey:true
        },
        {
            name:"name",
            type:"string",
            required:true,
            validators:[
            ],
            primaryKey:true
        },
        {
            name:"description",
            type:"string",
            validators:[
            ]
        },
        {
            name:"className",
            type:"string",
            required:true,
            validators:[
            ]
        },
        {
            name:"volatility",
            type:"boolean",
            defaultValue:"false",
            validators:[
            ]
        },
        {
            name:"durability",
            type:"boolean",
            defaultValue:"true",
            validators:[
            ]
        },
        {
            name:"recover",
            type:"boolean",
            defaultValue:"true",
            validators:[
            ]
        },
        {
            name:"dataMap",
            showIf:"false",
            type:"Object",
            validators:[
            ]
        }
    ]
})
