using System;

namespace WP6_056.Models
{
    public sealed class Customer
    {
        public Customer()
        {
            Id = 0;
            Name = "Unknown";
            Email = "unknown@example.com";
            IsActive = false;
        }

        public Customer(int id, string name, string email, bool isActive)
        {
            Id = id;
            Name = name ?? throw new ArgumentNullException(nameof(name));
            Email = email ?? throw new ArgumentNullException(nameof(email));
            IsActive = isActive;
        }

        public int Id { get; set; }
        public string Name { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;
        public bool IsActive { get; set; }
    }
}