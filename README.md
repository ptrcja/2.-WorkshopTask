# E-commerce Test Automation Project

## What This Project Does

This project automatically tests an online shopping website to make sure everything works correctly - from logging in to completing a purchase. Instead of a person clicking through the website manually, my program does it automatically and checks for problems.

### Business Impact
- **Saves Time**: Tests that took 2 hours manually now run in 5 minutes
- **Finds Problems**: Catches issues before customers experience them
- **Always Consistent**: Tests run the same way every time

## What Gets Tested

The automated tests check the complete shopping experience:

1. **Login** → Can customers sign in?
2. **Browse Products** → Are products displayed correctly?
3. **Check Discounts** → Is the 20% discount applied?
4. **Add to Cart** → Does the shopping cart work?
5. **Checkout** → Can orders be completed?
6. **Order History** → Are orders tracked properly?

## Real Test Example

The tests are written in plain English so everyone can understand:

```
Given a customer logs into the store
When they buy 5 sweaters in size M
Then they should see a 20% discount
And their order should appear in order history
```

## How It Works

Think of it like a very fast, tireless employee who:
- Opens the website
- Clicks all the right buttons in order
- Fills out forms
- Takes screenshots of important pages
- Reports any problems found

## Results

| What We Test | Status | Time Saved |
|--------------|--------|------------|
| Complete Purchase Flow | Working | 2 hours per test |
| Multiple Product Sizes | Working | Tests 3 sizes at once |
| Order Verification | Working | Instant validation |

## Technologies Used

- **Selenium**: Controls the web browser
- **Java**: Programming language
- **Cucumber**: Makes tests readable like English
- **Page Object Model**: Keeps code organized and maintainable

## Proof of Success

The system automatically takes screenshots of successful orders, providing visual confirmation that everything works correctly.


https://github.com/user-attachments/assets/ff23c1c0-2ca1-4de7-97c6-a5d76e378344


## Running the Tests

```bash
# Run all shopping tests
mvn test

# View test report
Open: target/cucumber-report.html
```

## My Learnings

- I expanded my skills in Selenium and Cucumber to create these tests. 
- The business benefit is that we can quickly find problems before real customers do.  
- My tests validate high-impact functionality like discount pricing accuracy and shopping cart calculations - errors in these areas directly impact customer satisfaction and can result in lost sales.
