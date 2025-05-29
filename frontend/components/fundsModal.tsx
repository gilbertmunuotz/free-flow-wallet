/* eslint-disable @typescript-eslint/no-explicit-any */
'use client'

import { DialogFooter, DialogHeader, DialogTitle, DialogClose } from "@/components/ui/dialog"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue, } from "@/components/ui/select"
import { Label } from "./ui/label"
import { Input } from "./ui/input"
import { Button } from "./ui/button"
import { PostFunds } from "@/app/funds/_action"
import { toast } from "sonner"
import { useRouter } from "next/navigation";
import { useState } from "react"


export function FundsModal() {

    const router = useRouter();

    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setLoading(true);

        const formData = new FormData(e.currentTarget);

        try {
            await PostFunds(formData)
            router.refresh();
            toast.success("Funding Added Successfully!")
        } catch (error: any) {
            toast.error(error.message || "Error Occured")
        }
        finally {
            setLoading(false);
        }
    };

    return (
        <DialogHeader>
            <DialogTitle className="text-center">New Fund</DialogTitle>
            <form onSubmit={handleSubmit}>
                <div className="flex flex-col gap-6">
                    <Select name="providerName" required>
                        <SelectTrigger className="w-full">
                            <SelectValue placeholder="Choose Merchant" />
                        </SelectTrigger>
                        <SelectContent>
                            <SelectItem value="crdb">CRDB</SelectItem>
                            <SelectItem value="nmb">NMB</SelectItem>
                            <SelectItem value="airtel_money">Airtel Money</SelectItem>
                        </SelectContent>
                    </Select>

                    <div className="grid gap-3">
                        <Label htmlFor="amount">Amount</Label>
                        <Input
                            name="amount"
                            type="text"
                            placeholder="20"
                            required
                        />
                    </div>
                    <DialogFooter>
                        <DialogClose asChild>
                            {loading ? (
                                <Button type="submit" className="cursor-not-allowed">Saving...</Button>
                            ) : (
                                <Button type="submit">Save</Button>
                            )}
                        </DialogClose>
                    </DialogFooter>
                </div>
            </form>
        </DialogHeader >
    )
}
